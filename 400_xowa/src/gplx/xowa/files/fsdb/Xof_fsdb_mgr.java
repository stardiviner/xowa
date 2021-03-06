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
package gplx.xowa.files.fsdb; import gplx.*; import gplx.xowa.*; import gplx.xowa.files.*;
public interface Xof_fsdb_mgr {
	String                              Key();
	gplx.xowa.files.bins.Xof_bin_mgr    Bin_mgr();
	gplx.fsdb.meta.Fsm_mnt_mgr          Mnt_mgr();
	void                                Init_by_wiki(Xow_wiki wiki);
	void                                Fsdb_search_by_list(List_adp itms, Xow_wiki wiki, Xoa_page page, gplx.xowa.guis.cbks.js.Xog_js_wkr js_wkr);
	void                                Rls();
}
