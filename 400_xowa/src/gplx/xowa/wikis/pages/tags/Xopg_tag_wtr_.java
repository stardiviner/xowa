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
package gplx.xowa.wikis.pages.tags; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.pages.*;
public class Xopg_tag_wtr_ {
	public static void Add__core(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url css_dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "core");
		head_tags.Add(Xopg_tag_itm.New_js_file(css_dir.GenSubFil_nest("Namespace_.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(css_dir.GenSubFil_nest("String_.js")));
	}
	public static void Add__xocss(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url css_dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xocss", "core");
		head_tags.Add(Xopg_tag_itm.New_css_file(css_dir.GenSubFil_nest("xocss_core-0.0.1.css")));
		head_tags.Add(Xopg_tag_itm.New_css_file(css_dir.GenSubFil_nest("xoimg_core-0.0.1.css")));
	}
	public static void Add__xohelp(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url css_dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xocss", "help");
		head_tags.Add(Xopg_tag_itm.New_css_file(css_dir.GenSubFil_nest("xohelp-0.0.1.css")));
		head_tags.Add(Xopg_tag_itm.New_js_file(css_dir.GenSubFil_nest("xohelp-0.0.1.js")));
	}
	public static void Add__xolog(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xolog");
		head_tags.Add(Xopg_tag_itm.New_css_file(dir.GenSubFil_nest("xo.log.css")));
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("xo.log.js")));
	}
	public static void Add__xotmpl(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xotmpl");
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("xo.tmpl.js")));
	}
	public static void Add__xoelem(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xoelem");
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("xo.elem.js")));
	}
	public static void Add__xonotify(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xonotify");
		head_tags.Add(Xopg_tag_itm.New_js_file(http_root.GenSubFil_nest("bin", "any", "xowa", "html", "res", "lib", "notifyjs", "notifyjs-0.3.1.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("xo.notify.js")));
	}
	public static void Add__xoajax(Xopg_tag_mgr head_tags, Io_url http_root, Xoa_app app) {
		Io_url dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "xoajax");
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("xo.app.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest(Get_app_js_file(app))));
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("xo.server.js")));
	}
	public static void Add__gui__progbars(Xopg_tag_mgr head_tags, Io_url http_root) {
		Io_url dir = http_root.GenSubDir_nest("bin", "any", "xowa", "html", "res", "src", "xowa", "gui", "progbars");
		head_tags.Add(Xopg_tag_itm.New_css_file(dir.GenSubFil_nest("Progbar.css")));
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("Progbar.js")));
		head_tags.Add(Xopg_tag_itm.New_js_file(dir.GenSubFil_nest("Progbar_util.js")));
	}
	private static String Get_app_js_file(Xoa_app app) {
		if (app.Mode().Tid_is_http()) return "xo.app.http_server.js";
		return gplx.core.envs.Op_sys.Cur().Tid_is_drd() ? "xo.app.drd.js" : "xo.app.swt.js";
	}
	public static void Add__mustache(Xopg_tag_mgr head_tags, Io_url http_root) {
		head_tags.Add(Xopg_tag_itm.New_js_file(http_root.GenSubFil_nest("bin", "any", "xowa", "html", "res", "lib", "mustache", "mustache-2.2.1.js")));
	}
	public static void Add__jquery(Xopg_tag_mgr head_tags, Io_url http_root) {
		head_tags.Add(Xopg_tag_itm.New_js_file(http_root.GenSubFil_nest("bin", "any", "xowa", "html", "res", "lib", "jquery", "jquery-1.11.3.js")));
	}
}
