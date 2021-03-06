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
package gplx.gfui.controls.customs; import gplx.*; import gplx.gfui.*; import gplx.gfui.controls.*;
import gplx.core.lists.*;
import gplx.gfui.controls.gxws.*; import gplx.gfui.controls.elems.*;
public class GfuiCheckListBox extends GfuiElemBase {
	public void Items_reverse() {checkListBox.Items_reverse();}
	public void Items_count() {checkListBox.Items_count();}
	public void Items_setAt(int i, boolean v) {checkListBox.Items_setCheckedAt(i, v);}
	public void Items_setAll(boolean v) {checkListBox.Items_setAll(v);}
	public void Items_clear() {checkListBox.Items_clear();}
	public void Items_add(Object item, boolean v) {checkListBox.Items_add(item, v);}
	public List_adp Items_getAll() {return checkListBox.Items_getAll();}
	public List_adp Items_getChecked() {return checkListBox.Items_getChecked();}

	GxwCheckListBox checkListBox;
	@Override public GxwElem UnderElem_make(Keyval_hash ctorArgs) {return new GxwCheckListBox_lang();}
	@Override public void ctor_GfuiBox_base(Keyval_hash ctorArgs) {
		super.ctor_GfuiBox_base(ctorArgs);
		this.checkListBox = (GxwCheckListBox)UnderElem();
	}
	public static GfuiCheckListBox new_() {
		GfuiCheckListBox rv = new GfuiCheckListBox();
		rv.ctor_GfuiBox_base(GfuiElem_.init_focusAble_true_());
		return rv;
	}
}
