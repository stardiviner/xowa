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
package gplx.gfui.kits.core; import gplx.*; import gplx.gfui.*; import gplx.gfui.kits.*;
import gplx.gfui.imgs.*;
public interface Gfui_mnu_grp extends Gfui_mnu_itm {
	String Root_key();
	void Itms_clear();
	boolean Disposed();
	Gfui_mnu_itm Itms_add_btn_cmd	(String txt, ImageAdp img, Gfo_invk invk, String invk_cmd);
	Gfui_mnu_itm Itms_add_btn_msg	(String txt, ImageAdp img, Gfo_invk invk, Gfo_invk_root_wkr root_wkr, GfoMsg msg);
	Gfui_mnu_itm Itms_add_chk_msg	(String txt, ImageAdp img, Gfo_invk invk, Gfo_invk_root_wkr root_wkr, GfoMsg msg_n, GfoMsg msg_y);
	Gfui_mnu_itm Itms_add_rdo_msg	(String txt, ImageAdp img, Gfo_invk invk, Gfo_invk_root_wkr root_wkr, GfoMsg msg);
	Gfui_mnu_grp Itms_add_grp		(String txt, ImageAdp img);
	Gfui_mnu_itm Itms_add_separator();
}
