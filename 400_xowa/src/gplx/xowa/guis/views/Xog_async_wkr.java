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
package gplx.xowa.guis.views; import gplx.*; import gplx.xowa.*; import gplx.xowa.guis.*;
import gplx.core.threads.*;
import gplx.xowa.wikis.pages.lnkis.*;
import gplx.xowa.guis.cbks.js.*;
public class Xog_async_wkr {
	public static void Async(Xog_tab_itm tab) {Xog_async_wkr.Async(tab.Page(), tab.Html_itm());}
	public static void Async(Xoae_page page, Xog_html_itm js_wkr) {
		if (page == null) return;	// TEST: occurs during Xog_win_mgr_tst

		// get wiki
		Xowe_wiki wiki = page.Wikie(); Xoae_app app = wiki.Appe(); Gfo_usr_dlg usr_dlg = app.Usr_dlg();
		app.Usr_dlg().Log_many("", "", "page.async: url=~{0}", page.Url().To_str());
		if (page.Url().Anch_str() != null)
			js_wkr.Scroll_page_by_id_gui(page.Url().Anch_str());
		if (usr_dlg.Canceled()) {
			usr_dlg.Prog_none("", "", "");
			app.Log_wtr().Queue_enabled_(false);
			return;
		}

		Async_imgs(usr_dlg, app, wiki, page, js_wkr);
		gplx.xowa.xtns.math.Xomath_latex_bldr.Async(app, page, js_wkr);
		Async_score(usr_dlg, app, page);
		Async_redlinks(usr_dlg, app, page, js_wkr);

		// cache maintenance
		usr_dlg.Prog_none("", "imgs.done", "");
		try {app.File_mgr().Cache_mgr().Compress_check();}
		catch (Exception e) {usr_dlg.Warn_many("", "", "page.thread.cache: page=~{0} err=~{1}", page.Ttl().Raw(), Err_.Message_gplx_full(e));}
		app.Usere().User_db_mgr().Cache_mgr().Page_end(app.Wiki_mgr());
		app.Log_wtr().Queue_enabled_(false);	// flush queue
	}
	private static void Async_imgs(Gfo_usr_dlg usr_dlg, Xoae_app app, Xowe_wiki wiki, Xoae_page page, Xog_js_wkr js_wkr) {
		// get images
		int len = page.File_queue().Count(); 
		if (len > 0) {
			usr_dlg.Prog_one("", "", "downloading images: ~{0}", len);
			try {page.File_queue().Exec(wiki, page);}
			catch (Exception e) {usr_dlg.Warn_many("", "", "page.thread.image: page=~{0} err=~{1}", page.Ttl().Raw(), Err_.Message_gplx_full(e));}
		}

		// if gallery.packed exists, call pack; NOTE:must fire even when there are 0 items in queue b/c hdump will restore images without placing in queue; PAGE:en.w:Mexico DATE:2016-08-14
		if (page.Html_data().Xtn_gallery_packed_exists())	// packed_gallery exists; fire js once; PAGE:en.w:National_Sculpture_Museum_(Valladolid); DATE:2014-07-21
			js_wkr.Html_gallery_packed_exec();

		// call imap
		if (	page.Html_data().Xtn_imap_exists()			// imap exists; DATE:2014-08-07
			&&	page.Html_data().Head_mgr().Itm__popups().Enabled()
			)
			js_wkr.Html_popups_bind_hover_to_doc();			// rebind all elements to popup
	}
	private static void Async_score(Gfo_usr_dlg usr_dlg, Xoae_app app, Xoae_page page) {
		// run other cmds
		if (page.Html_cmd_mgr().Count() > 0) {
			try {page.Html_cmd_mgr().Exec(app, page);}
			catch (Exception e) {usr_dlg.Warn_many("", "", "page.thread.cmds: page=~{0} err=~{1}", page.Ttl().Raw(), Err_.Message_gplx_full(e));}
		}
	}
	private static void Async_redlinks(Gfo_usr_dlg usr_dlg, Xoae_app app, Xoae_page page, Xog_js_wkr js_wkr) {
		if (page.Tab_data().Tab() == null) return;	// needed b/c Preview has page.Tab of null which causes null_ref error in redlinks
		Xopg_redlink_mgr.Run_async(page, js_wkr);
	}
}
