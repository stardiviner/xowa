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
package gplx.xowa.hdumps; import gplx.*; import gplx.xowa.*;
import gplx.dbs.*; import gplx.xowa.dbs.*; import gplx.xowa.dbs.tbls.*; import gplx.xowa.hdumps.dbs.*;
public class Xodb_hdump_mgr_setup {
	public static Xodb_file Hdump_db_file_init(Xodb_hdump_mgr hdump_mgr) {
		Xow_wiki wiki = hdump_mgr.Wiki();
		Xodb_mgr_sql db_mgr_as_sql = wiki.Db_mgr_as_sql();
		Xodb_file rv = db_mgr_as_sql.Fsys_mgr().Get_tid_root(Xodb_file_tid.Tid_html);
		if (rv == null) rv = Setup(db_mgr_as_sql);
		hdump_mgr.Text_tbl().Provider_(rv.Provider());
		return rv;
	}
	private static Xodb_file Setup(Xodb_mgr_sql db_mgr) {
		Xodb_fsys_mgr fsys_mgr = db_mgr.Fsys_mgr();
		Update_core(fsys_mgr);
		Xodb_file html_db_file = Create_db(db_mgr, fsys_mgr);
		Create_idx(html_db_file);
		return html_db_file;
	}
	private static void Update_core(Xodb_fsys_mgr fsys_mgr) {
		Db_provider core_provider = fsys_mgr.Provider_core();
		try {
			Xodb_xowa_cfg_tbl.Insert_str(core_provider, "db.meta", "html_db.exists", "y");
			core_provider.Exec_sql("ALTER TABLE page ADD COLUMN page_html_db_id integer NOT NULL DEFAULT '-1'");
		}	catch (Exception e) {Gfo_usr_dlg_._.Warn_many("", "", "failed to update core: db=~{0} err=~{1}", core_provider.Conn_info().Str_raw(), Err_.Message_gplx(e));}
	}
	private static Xodb_file Create_db(Xodb_mgr_sql db_mgr, Xodb_fsys_mgr fsys_mgr) {
		Xodb_file html_db_file = fsys_mgr.Make(Xodb_file_tid.Tid_html);
		html_db_file.Provider().Exec_sql(Hdump_text_tbl.Tbl_sql);
		db_mgr.Tbl_xowa_db().Commit_all(fsys_mgr.Provider_core(), db_mgr.Fsys_mgr().Files_ary());
		return html_db_file;
	}
	private static void Create_idx(Xodb_file html_db_file) {
		Sqlite_engine_.Idx_create(html_db_file.Provider(), Hdump_text_tbl.Idx_core);
	}
}
