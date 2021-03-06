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
public class Gfui_dlg_dir_ {
	public static final    Gfui_dlg_dir Noop = new Gfui_dlg_dir__noop();
}
class Gfui_dlg_dir__noop implements Gfui_dlg_dir {
	public String Ask() {return "";}
	public Gfui_dlg_dir Init_msg_(String v) {return this;}
	public Gfui_dlg_dir Init_text_(String v) {return this;}
	public Gfui_dlg_dir Init_dir_(Io_url v) {return this;}
}
