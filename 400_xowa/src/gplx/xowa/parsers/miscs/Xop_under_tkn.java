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
package gplx.xowa.parsers.miscs; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*;
public class Xop_under_tkn extends Xop_tkn_itm_base {
	public Xop_under_tkn(int bgn, int end, int under_tid) {this.under_tid = under_tid; this.Tkn_ini_pos(false, bgn, end);}
	@Override public byte Tkn_tid() {return Xop_tkn_itm_.Tid_under;}
	public int Under_tid() {return under_tid;} private int under_tid;
}
