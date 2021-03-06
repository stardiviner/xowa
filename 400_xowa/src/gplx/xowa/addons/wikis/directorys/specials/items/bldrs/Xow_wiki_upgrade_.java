/*
XOWA: the XOWA Offline Wiki Application
Copyright (C) 2012 gnosygnu@gmail.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package gplx.xowa.addons.wikis.directorys.specials.items.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.directorys.*; import gplx.xowa.addons.wikis.directorys.specials.*; import gplx.xowa.addons.wikis.directorys.specials.items.*;
import gplx.dbs.*; import gplx.dbs.cfgs.*;
import gplx.xowa.wikis.data.*; import gplx.xowa.wikis.data.tbls.*; import gplx.xowa.addons.wikis.ctgs.dbs.*;
import gplx.xowa.addons.wikis.directorys.dbs.*;
class Xow_wiki_upgrade_ {
	// correlates loosely to App_.Version; however, should only change when new cases are added to this class
	private static final int 
	  Upgrade_version__v00  = 515
	, Upgrade_version__v01  = 516
	, Upgrade_version__cur  = 516	// must match latest version
	;
	public static void Upgrade_wiki(Xoae_app app, byte[] domain, Io_url dir_url) {
		// get conn
		Io_url core_db_url = gplx.xowa.wikis.data.Xow_db_file__core_.Find_core_fil_or_null(dir_url, String_.new_u8(domain));
		if (core_db_url == null) {
			throw Err_.new_wo_type("failed to find core_db for wiki; wiki=~{domain} dir=~{dir_url}", domain, dir_url);
		}
		Db_conn core_db_conn = Db_conn_bldr.Instance.Get_or_fail(core_db_url);

		// verify json
		Xowdir_wiki_props_mgr core_db_props = Xowdir_wiki_props_mgr_.New_xowa(app, core_db_url);
		core_db_props.Verify(Bool_.N, String_.new_u8(domain), core_db_url);

		// get cfg
		Db_cfg_tbl cfg_tbl = Xowd_cfg_tbl_.Get_or_fail(core_db_conn);
		int upgrade_version = cfg_tbl.Select_int_or(Xowd_cfg_key_.Key__wiki__upgrade__version, Upgrade_version__v00);

		// wiki is up-to-date; exit;
		if (upgrade_version == Upgrade_version__cur) return;

		// upgrades related to v00
		if (upgrade_version == Upgrade_version__v00) {
			Gfo_usr_dlg_.Instance.Log_many("", "", "xo.wiki.upgrade:upgrading; db=~{0} cur=~{1} new=~{2}", core_db_url.Raw(), upgrade_version, Upgrade_version__v01);

			// cat_link: if cat_link.cl_sortkey_prefix doesn't exist, then cat_link is old format; drop it and add the new one
			try {
				if (!core_db_conn.Meta_fld_exists(Xodb_cat_link_tbl.TBL_NAME, Xodb_cat_link_tbl.FLD__cl_sortkey_prefix)) {
					Gfo_usr_dlg_.Instance.Log_many("", "", "xo.personal:cat_link upgrade; fil=~{0}", core_db_url.Raw());
					core_db_conn.Meta_tbl_delete(Xodb_cat_link_tbl.TBL_NAME);
					core_db_conn.Meta_tbl_assert(new Xodb_cat_link_tbl(core_db_conn));
				}
			} catch (Exception e) {
				Gfo_usr_dlg_.Instance.Warn_many("", "", "xo.personal:cat_link upgrade failed; err=~{0}", Err_.Message_gplx_log(e));
			}

			// page.cat_db_id: if page.cat_db_id doesn't exist, then add it
			try {
				if (!core_db_conn.Meta_fld_exists(Xowd_page_tbl.TBL_NAME, Xowd_page_tbl.FLD__page_cat_db_id)) {
					Gfo_usr_dlg_.Instance.Log_many("", "", "xo.personal:page.page_cat_db_id upgrade; fil=~{0}", core_db_url.Raw());
					core_db_conn.Meta_fld_append(Xowd_page_tbl.TBL_NAME, Dbmeta_fld_itm.new_int(Xowd_page_tbl.FLD__page_cat_db_id).Default_(-1));
				}
			} catch (Exception e) {
				Gfo_usr_dlg_.Instance.Warn_many("", "", "xo.personal:page.page_cat_db_id upgrade failed; err=~{0}", Err_.Message_gplx_log(e));
			}

			// check for page_ids < 1
			// select from page_tbl for page_id < 1
			Xow_db_mgr db_mgr = new Xow_db_mgr(dir_url, String_.new_u8(domain));
			db_mgr.Init_by_load(core_db_url);
			Xowd_page_tbl page_tbl = db_mgr.Db__core().Tbl__page();
			List_adp page_ids_list = List_adp_.New();
			Db_rdr page_rdr = page_tbl.Conn().Stmt_sql(Db_sql_.Make_by_fmt(String_.Ary("SELECT page_id FROM page WHERE page_id < 1"))).Exec_select__rls_auto();
			try {
				while (page_rdr.Move_next()) {
					page_ids_list.Add(page_rdr.Read_int("page_id"));
				}
			} finally {page_rdr.Rls();}

			// update page_id if any found
			int page_ids_len = page_ids_list.Len();
			if (page_ids_len > 0) {
				int next_id = db_mgr.Db__core().Tbl__cfg().Select_int(Xowd_cfg_key_.Grp__db, Xowd_cfg_key_.Key__wiki__page__id_next);
				for (int i = 0; i < page_ids_len; i++) {
					int old_page_id = (int)page_ids_list.Get_at(i);
					int new_page_id = next_id + i;
					Xopg_db_mgr.Update_page_id(db_mgr, old_page_id, new_page_id);
				}
				db_mgr.Db__core().Tbl__cfg().Upsert_int(Xowd_cfg_key_.Grp__db, Xowd_cfg_key_.Key__wiki__page__id_next, next_id + page_ids_len);
			}

			cfg_tbl.Upsert_int(Xowd_cfg_key_.Key__wiki__upgrade__version, Upgrade_version__v01);
		}
	}
}
