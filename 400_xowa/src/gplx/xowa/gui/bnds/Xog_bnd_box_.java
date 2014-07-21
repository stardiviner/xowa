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
package gplx.xowa.gui.bnds; import gplx.*; import gplx.xowa.*; import gplx.xowa.gui.*;
import gplx.gfui.*; import gplx.xowa.gui.views.*; import gplx.xowa.gui.cmds.*;
public class Xog_bnd_box_ {
	public static final String Key_browser = "browser", Key_browser_url = "browser.url", Key_browser_search = "browser.search", Key_browser_html = "browser.html", Key_browser_find = "browser.find", Key_browser_prog = "browser.prog", Key_browser_info = "browser.info";
	public static final String Gui_browser = "Window", Gui_browser_url = "Url bar", Gui_browser_search = "Search box", Gui_browser_html = "HTML browser", Gui_browser_find = "Find box", Gui_browser_prog = "Status bar", Gui_browser_info = "System Messages box";
	public static final int Tid__max = 7, Tid_browser = 0, Tid_browser_url = 1, Tid_browser_search = 2, Tid_browser_html = 3, Tid_browser_find = 4, Tid_browser_prog = 5, Tid_browser_info = 6;
	public static final int Ary_len = Tid__max;
	public static Xog_bnd_box[] Ary() {
		if (ary != null) return ary;
		ary = new Xog_bnd_box[Tid__max];
		ary_init(ary, Tid_browser				, Key_browser);
		ary_init(ary, Tid_browser_url			, Key_browser_url);
		ary_init(ary, Tid_browser_search		, Key_browser_search);
		ary_init(ary, Tid_browser_html			, Key_browser_html);
		ary_init(ary, Tid_browser_find			, Key_browser_find);
		ary_init(ary, Tid_browser_prog			, Key_browser_prog);
		ary_init(ary, Tid_browser_info			, Key_browser_info);
		return ary;
	}	private static Xog_bnd_box[] ary;
	private static void ary_init(Xog_bnd_box[] ary, int tid, String key) {ary[tid] = new Xog_bnd_box(tid, key);}
	public static int[] Xto_sys_int_ary(String s) {
		String[] ary = String_.Split(s, "|");
		int len = ary.length;
		int[] rv = new int[len];
		for (int i = 0; i < len; i++)
			rv[i] = Xto_sys_int(ary[i]);
		return rv;
	}
	public static int Xto_sys_int(String s) {
		if		(String_.Eq(s, Key_browser))			return Tid_browser;
		else if	(String_.Eq(s, Key_browser_url))		return Tid_browser_url;
		else if	(String_.Eq(s, Key_browser_search))		return Tid_browser_search;
		else if	(String_.Eq(s, Key_browser_html))		return Tid_browser_html;
		else if	(String_.Eq(s, Key_browser_find))		return Tid_browser_find;
		else if	(String_.Eq(s, Key_browser_prog))		return Tid_browser_prog;
		else if	(String_.Eq(s, Key_browser_info))		return Tid_browser_info;
		else											throw Err_.unhandled(s);
	}
	public static String Xto_sys_str(int v) {
		switch (v) {
			case Tid_browser:					return Key_browser;
			case Tid_browser_url:				return Key_browser_url;
			case Tid_browser_search:			return Key_browser_search;
			case Tid_browser_html:				return Key_browser_html;
			case Tid_browser_find:				return Key_browser_find;
			case Tid_browser_prog:				return Key_browser_prog;
			case Tid_browser_info:				return Key_browser_info;
			default:							throw Err_.unhandled(v);
		}
	}
	public static String Xto_gui_str(int v) {
		switch (v) {
			case Tid_browser:					return Gui_browser;
			case Tid_browser_url:				return Gui_browser_url;
			case Tid_browser_search:			return Gui_browser_search;
			case Tid_browser_html:				return Gui_browser_html;
			case Tid_browser_find:				return Gui_browser_find;
			case Tid_browser_prog:				return Gui_browser_prog;
			case Tid_browser_info:				return Gui_browser_info;
			default:							throw Err_.unhandled(v);
		}
	}
	public static int Xby_gui_str(String s) {
		if		(String_.Eq(s, Gui_browser))			return Tid_browser;
		else if	(String_.Eq(s, Gui_browser_url))		return Tid_browser_url;
		else if	(String_.Eq(s, Gui_browser_search))		return Tid_browser_search;
		else if	(String_.Eq(s, Gui_browser_html))		return Tid_browser_html;
		else if	(String_.Eq(s, Gui_browser_find))		return Tid_browser_find;
		else if	(String_.Eq(s, Gui_browser_prog))		return Tid_browser_prog;
		else if	(String_.Eq(s, Gui_browser_info))		return Tid_browser_info;
		else											throw Err_.unhandled(s);
	}
	public static void Set_bnd_for_grp(byte mode, Xog_win_itm win, Xog_cmd_mgr_invk invk_mgr, Xog_bnd_box box, Xog_bnd_itm itm, IptArg ipt) {
		GfuiElem box_elem = null;
		String grp_key = box.Key();
		if		(String_.Eq(grp_key, Xog_bnd_box_.Key_browser_html))			{Set_bnd_for_tab(mode, win.Tab_mgr(), invk_mgr, box, itm, ipt); return;}
		else if	(String_.Eq(grp_key, Xog_bnd_box_.Key_browser))					box_elem = win.Win_box();
		else if	(String_.Eq(grp_key, Xog_bnd_box_.Key_browser_url))				box_elem = win.Url_box();
		else if	(String_.Eq(grp_key, Xog_bnd_box_.Key_browser_search))			box_elem = win.Search_box();
		else if	(String_.Eq(grp_key, Xog_bnd_box_.Key_browser_find))			box_elem = win.Find_box();
		else if	(String_.Eq(grp_key, Xog_bnd_box_.Key_browser_prog))			box_elem = win.Prog_box();
		else if	(String_.Eq(grp_key, Xog_bnd_box_.Key_browser_info))			box_elem = win.Info_box();
		else																	throw Err_.new_fmt_("unknown box: {0}", grp_key);
		Set_bnd_for_elem(mode, box, box_elem, invk_mgr, itm, ipt);
	}
	public static void Set_bnd_for_elem(byte mode, Xog_bnd_box box, GfuiElem box_elem, Xog_cmd_mgr_invk invk_mgr, Xog_bnd_itm itm, IptArg ipt) {
		switch (mode) {
			case Set_add:
				if (!IptArg_.Is_null_or_none(itm.Ipt()))
					IptBnd_.cmd_to_(IptCfg_.Null, box_elem, invk_mgr, itm.Cmd(), itm.Ipt());
				break;
			case Set_del_key:
				box_elem.IptBnds().Del_by_key(itm.Cmd());	// NOTE: delete by cmd, since GfuiIpts use cmd for key
				break;
			case Set_del_ipt:
				box_elem.IptBnds().Del_by_ipt(ipt);
				break;
			default: throw Err_.unhandled(mode);
		}
	}
	private static void Set_bnd_for_tab(byte mode, Xog_tab_mgr tab_mgr, Xog_cmd_mgr_invk invk_mgr, Xog_bnd_box box, Xog_bnd_itm itm, IptArg ipt) {
		int len = tab_mgr.Tabs_len();
		for (int i = 0; i < len; i++) {
			Xog_tab_itm tab_itm = tab_mgr.Tabs_get_at(i);
			Set_bnd_for_elem(mode, box, tab_itm.Html_box(), invk_mgr, itm, ipt);
		}
	}
	public static final byte Set_add = 0, Set_del_key = 1, Set_del_ipt = 2;
}
