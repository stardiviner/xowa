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
package gplx.xowa.langs.msgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.langs.*;
import gplx.core.brys.fmtrs.*;
public class Xol_msg_itm {
	public Xol_msg_itm(int id, byte[] key) {this.id = id; this.key = key;}
	public int		Id()				{return id;} private final    int id;
	public byte[]	Key()				{return key;} private final    byte[] key;
	public byte[]	Val()				{return val;} private byte[] val;
	public int		Defined_in()		{return defined_in;} private int defined_in;
	public boolean	Defined_in_none()	{return defined_in == Defined_in__none;}
	public boolean	Has_fmt_arg()		{return has_fmt_arg;} private boolean has_fmt_arg;
	public boolean		Has_tmpl_txt()		{return has_tmpl_txt;} private boolean has_tmpl_txt;
	public boolean		Dirty()				{return dirty;} private boolean dirty;	// BLDR:
	
	public Xol_msg_itm Defined_in_(int v) {defined_in = v; return this;}
	public Xol_msg_itm Dirty_(boolean v) {dirty = v; return this;}

	public void Atrs_set(byte[] val, boolean has_fmt_arg, boolean has_tmpl_txt) {
		this.val = val; this.has_fmt_arg = has_fmt_arg; this.has_tmpl_txt = has_tmpl_txt;
	}
	public byte[] Fmt(Bry_bfr bfr, Bry_fmtr fmtr, Object... args) {
		fmtr.Fmt_(val);
		fmtr.Bld_bfr_many(bfr, args);
		return bfr.To_bry_and_clear();
	}
	public byte[] Fmt_tmp(Bry_bfr bfr, Bry_fmtr fmtr, byte[] tmp_val, Object... args) {
		fmtr.Fmt_(tmp_val);
		fmtr.Bld_bfr_many(bfr, args);
		return bfr.To_bry_and_clear();
	}
	public static final    int Defined_in__unknown = 0, Defined_in__lang = 1, Defined_in__wiki = 2, Defined_in__none = 3;	// NOTE: unknown not manually used, but is different than none (which means missing?)
}
