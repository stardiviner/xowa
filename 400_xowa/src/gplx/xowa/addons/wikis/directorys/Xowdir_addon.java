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
package gplx.xowa.addons.wikis.directorys; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*;
import gplx.xowa.addons.wikis.directorys.dbs.*;
import gplx.xowa.addons.wikis.directorys.specials.items.*; import gplx.xowa.addons.wikis.directorys.specials.lists.*;
import gplx.xowa.htmls.bridges.*;
import gplx.dbs.*;
import gplx.xowa.specials.*;
public class Xowdir_addon implements Xoax_addon_itm, Xoax_addon_itm__special, Xoax_addon_itm__json {
	public Xow_special_page[] Special_pages() {
		return new Xow_special_page[]
		{ Xowdir_item_special.Prototype
		, Xowdir_list_special.Prototype
		};
	}
	public Bridge_cmd_itm[] Json_cmds() {
		return new Bridge_cmd_itm[]
		{ Xowdir_item_bridge.Prototype
		, Xowdir_list_bridge.Prototype
		};
	}

	public String Addon__key() {return ADDON__KEY;} private static final String ADDON__KEY = "xowa.user.wiki.regy";
//		public static Xowdir_addon Addon__get(Xow_wiki wiki) {
//			Xowdir_addon rv = (Xowdir_addon)wiki.Addon_mgr().Itms__get_or_null(ADDON__KEY);
//			if (rv == null) {
//				rv = new Xowdir_addon(wiki.App().User().User_db_mgr().Conn());
//				wiki.Addon_mgr().Itms__add(rv);
//			}
//			return rv;
//		}
	public static void Init(Xoae_app app) {
		// exit if none found
		Db_conn conn = app.User().User_db_mgr().Conn();
		if (!conn.Meta_tbl_exists(Xowdir_wiki_tbl.Tbl_name_dflt)) return;

		// register
		Xowdir_db_mgr db_mgr = new Xowdir_db_mgr(conn);
		Xowdir_wiki_itm[] itms = db_mgr.Tbl__wiki().Select_all();
		int len = itms.length;
		for (int i = 0; i < len; ++i) {
			Xowdir_wiki_itm itm = itms[i];
			try {
				gplx.xowa.addons.wikis.directorys.specials.items.bldrs.Xow_wiki_factory.Load_personal(app, Bry_.new_u8(itm.Domain()), itm.Url().OwnerDir());
			} catch (Exception exc) {// handles failures if wiki is deleted, but item is still in wiki_list table
				Gfo_usr_dlg_.Instance.Warn_many("", "", "personal_wiki: failed to open personal wiki; domain=~{0} url=~{1} err=~{2}", itm.Domain(), itm.Url(), Err_.Message_gplx_log(exc));
			}
		}
	}
}
