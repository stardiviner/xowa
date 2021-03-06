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
package gplx.xowa.addons.bldrs.updates.files; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.updates.*;
import gplx.xowa.bldrs.wkrs.*;
import gplx.xowa.addons.bldrs.utils_rankings.bldrs.*;
import gplx.xowa.addons.bldrs.updates.files.find_missings.*;
public class Xodel_addon implements Xoax_addon_itm, Xoax_addon_itm__bldr {
	public Xob_cmd[] Bldr_cmds() {
		return new Xob_cmd[] 
		{ Xodel_make_cmd.Prototype
		, Xodel_exec_cmd.Prototype
		, Xodel_small_cmd.Prototype
		, Xodel_find_missing_cmd.Prototype
		};
	}

	public String Addon__key() {return "xowa.updates.files";}
}
