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
package gplx.xowa.bldrs.wms.revs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*; import gplx.xowa.bldrs.wms.*;
class Wmapi_itm__pge {
	public int		Page_id() {return page_id;} private int page_id;
	public int		Page_ns() {return page_ns;} private int page_ns;
	public byte[]	Page_ttl() {return page_ttl;} private byte[] page_ttl;
	public Wmapi_itm__rvn[] Rvn_ary() {return rvn_ary;} private Wmapi_itm__rvn[] rvn_ary = Wmapi_itm__rvn.Ary_empty;
	public Wmapi_itm__ttl[] Tml_ary() {return tml_ary;} private Wmapi_itm__ttl[] tml_ary = Wmapi_itm__ttl.Ary_empty;
	public Wmapi_itm__ttl[] Img_ary() {return img_ary;} private Wmapi_itm__ttl[] img_ary = Wmapi_itm__ttl.Ary_empty;
	public Wmapi_itm__ctg[] Ctg_ary() {return ctg_ary;} private Wmapi_itm__ctg[] ctg_ary = Wmapi_itm__ctg.Ary_empty;
	public void Rvn_ary_(Wmapi_itm__rvn... v) {this.rvn_ary = v;}
	public void Tml_ary_(Wmapi_itm__ttl... v) {this.tml_ary = v;}
	public void Img_ary_(Wmapi_itm__ttl... v) {this.img_ary = v;}
	public void Ctg_ary_(Wmapi_itm__ctg... v) {this.ctg_ary = v;}
	public Wmapi_itm__rvn Rvn_itm_last() {return rvn_ary[rvn_ary.length - 1];}
	public Wmapi_itm__pge Init_ttl(int page_ns, byte[] page_ttl) {
		this.page_ns = page_ns; this.page_ttl = page_ttl;
		return this;
	}
	public Wmapi_itm__pge Init_id(int page_id) {
		this.page_id = page_id;
		return this;
	}
	public boolean Eq_meta(Wmapi_itm__pge rhs_page, int idx) {
		Wmapi_itm__rvn lhs = rvn_ary[idx];
		Wmapi_itm__rvn rhs = rhs_page.rvn_ary[idx];
		return lhs.Rvn_len() == rhs.Rvn_len() && Bry_.Eq(lhs.Rvn_time(), rhs.Rvn_time());
	}
}
class Wmapi_itm__ttl {
	public Wmapi_itm__ttl(int ns_id, byte[] ttl_bry) {this.ns_id = ns_id; this.ttl_bry = ttl_bry;}
	public int		Ns_id()		{return ns_id;} private final int ns_id;
	public byte[]	Ttl_bry()	{return ttl_bry;} private final byte[] ttl_bry;
	public static final Wmapi_itm__ttl[] Ary_empty = new Wmapi_itm__ttl[0];
}
class Wmapi_itm__rvn {
	public int		Rvn_id() {return rvn_id;} private int rvn_id;
	public int		Rvn_len() {return rvn_len;} private int rvn_len;
	public byte[]	Rvn_time() {return rvn_time;} private byte[] rvn_time;
	public byte[]	Rvn_user() {return rvn_user;} private byte[] rvn_user;
	public byte[]	Rvn_note() {return rvn_note;} private byte[] rvn_note;
	public byte[]	Rvn_text() {return rvn_text;} private byte[] rvn_text;
	public void		Rvn_text_(byte[] v) {this.rvn_text = v;}
	public void Init(int rvn_id, int rvn_len, byte[] rvn_time, byte[] rvn_user, byte[] rvn_note) {
		this.rvn_id = rvn_id;
		this.rvn_len = rvn_len; this.rvn_time = rvn_time;
		this.rvn_user = rvn_user; this.rvn_note = rvn_note;
	}
	public static final Wmapi_itm__rvn[] Ary_empty = new Wmapi_itm__rvn[0];
}
class Wmapi_itm__ctg {
	public Wmapi_itm__ctg(int ctg_ns, byte[] ctg_ttl, byte[] ctg_sortkey, boolean ctg_sortprefix, byte[] ctg_time, boolean ctg_hidden) {
		this.ctg_ns = ctg_ns; this.ctg_ttl = ctg_ttl; this.ctg_sortkey = ctg_sortkey; this.ctg_sortprefix = ctg_sortprefix; this.ctg_time = ctg_time; this.ctg_hidden = ctg_hidden;
	}
	public int Ctg_ns() {return ctg_ns;} private final int ctg_ns;
	public byte[] Ctg_ttl() {return ctg_ttl;} private final byte[] ctg_ttl;
	public byte[] Ctg_sortkey() {return ctg_sortkey;} private final byte[] ctg_sortkey;
	public boolean Ctg_sortprefix() {return ctg_sortprefix;} private final boolean ctg_sortprefix;
	public byte[] Ctg_time() {return ctg_time;} private final byte[] ctg_time;
	public boolean Ctg_hidden() {return ctg_hidden;} private final boolean ctg_hidden;
	public static final Wmapi_itm__ctg[] Ary_empty = new Wmapi_itm__ctg[0];
}
