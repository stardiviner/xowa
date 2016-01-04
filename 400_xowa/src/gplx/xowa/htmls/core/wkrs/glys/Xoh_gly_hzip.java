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
package gplx.xowa.htmls.core.wkrs.glys; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.primitives.*; import gplx.core.brys.*; import gplx.core.threads.poolables.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*; import gplx.xowa.htmls.hrefs.*; import gplx.xowa.htmls.core.hzips.*;
import gplx.xowa.htmls.core.wkrs.bfr_args.*; import gplx.xowa.htmls.core.wkrs.imgs.atrs.*; import gplx.xowa.htmls.core.wkrs.lnkis.*; import gplx.xowa.htmls.core.wkrs.lnkis.anchs.*;
import gplx.xowa.wikis.nss.*; import gplx.xowa.wikis.ttls.*;
import gplx.xowa.files.*; import gplx.xowa.files.repos.*;
import gplx.xowa.xtns.gallery.*;
public class Xoh_gly_hzip implements Xoh_hzip_wkr, Gfo_poolable_itm {
	private final Xoh_gly_grp_wtr grp_wtr = new Xoh_gly_grp_wtr();
	public int Tid() {return Xoh_hzip_dict_.Tid__gly;}
	public String Key() {return Xoh_hzip_dict_.Key__gly;}
	public byte[] Hook() {return hook;} private byte[] hook;
	public Gfo_poolable_itm Encode1(Xoh_hzip_bfr bfr, Xoh_hdoc_wkr hdoc_wkr, Xoh_hdoc_ctx hctx, Xoh_page hpg, boolean wkr_is_root, byte[] src, Object data_obj) {
		Xoh_gly_grp_data data = (Xoh_gly_grp_data)data_obj;
		boolean xtra_atr	= flag_bldr.Set_as_bool(Flag__ul__xtra_atr		, data.Xtra_atr_exists());
		boolean xtra_cls	= flag_bldr.Set_as_bool(Flag__ul__xtra_cls		, data.Xtra_cls_exists());
		boolean xtra_style	= flag_bldr.Set_as_bool(Flag__ul__xtra_style	, data.Xtra_style_exists());
		flag_bldr.Set(Flag__gly_tid, data.Gly_tid());
		int itms_len = data.Itms__len();

		bfr.Add(hook);
		Xoh_hzip_int_.Encode(1, bfr, flag_bldr.Encode());
		Xoh_hzip_int_.Encode(1, bfr, data.Gly_w());
		if (xtra_cls) bfr.Add_hzip_mid(src, data.Xtra_cls_bgn(), data.Xtra_cls_end());
		if (xtra_style) bfr.Add_hzip_mid(src, data.Xtra_style_bgn(), data.Xtra_style_end());
		if (xtra_atr) bfr.Add_hzip_mid(src, data.Xtra_atr_bgn(), data.Xtra_atr_end());
		Xoh_hzip_int_.Encode(1, bfr, itms_len);
		for (int i = 0; i < itms_len; ++i) {
			Xoh_gly_itm_data itm_parser = data.Itms__get_at(i);
			bfr.Add_hzip_int(1, itm_parser.Li_w());
			bfr.Add_hzip_int(1, itm_parser.Div_1_w());
			bfr.Add_hzip_int(1, itm_parser.Div_2_margin());
			bfr.Add_byte((byte)(itm_parser.Capt_tid() + gplx.core.encoders.Base85_.A7_offset));
			bfr.Add_hzip_mid(src, itm_parser.Capt_bgn(), itm_parser.Capt_end());
			Xoh_hzip_wkr hzip_wkr = hctx.Pool_mgr__hzip().Mw__img();
			hzip_wkr.Encode1(bfr, hdoc_wkr, hctx, hpg, false, src, itm_parser.Img_parser());
			hzip_wkr.Pool__rls();
		}
		return this;
	}
	public void Decode1(Bry_bfr bfr, Xoh_hdoc_wkr hdoc_wkr, Xoh_hdoc_ctx hctx, Xoh_page hpg, Bry_rdr rdr, byte[] src, int src_bgn, int src_end, Xoh_data_itm data_itm) {
		int flag = rdr.Read_hzip_int(1); flag_bldr.Decode(flag);
		boolean xtra_atr	= flag_bldr.Get_as_bool(Flag__ul__xtra_atr);
		boolean xtra_cls	= flag_bldr.Get_as_bool(Flag__ul__xtra_cls);
		boolean xtra_style	= flag_bldr.Get_as_bool(Flag__ul__xtra_style);
		byte    cls_tid		= flag_bldr.Get_as_byte(Flag__gly_tid);
		byte[] cls_bry = Gallery_mgr_base_.Get_bry_by_tid(cls_tid);
		int ul_w = rdr.Read_hzip_int(1); 
		byte[] xtra_cls_bry = xtra_cls ? rdr.Read_bry_to(): null;
		byte[] xtra_style_bry = xtra_style ? rdr.Read_bry_to(): null;
		byte[] xtra_atr_bry = xtra_atr ? rdr.Read_bry_to(): null;
		int li_len = rdr.Read_hzip_int(1);
		int uid = hctx.Uid__gly__nxt();
		Xoh_gly_itm_wtr[] itm_ary = new Xoh_gly_itm_wtr[li_len];
		for (int i = 0; i < li_len; ++i) {
			Xoh_gly_itm_wtr itm_wtr = new Xoh_gly_itm_wtr();
			itm_ary[i] = itm_wtr;
			int li_w = rdr.Read_hzip_int(1);
			int div_1_w = rdr.Read_hzip_int(1);
			int div_2_margin = rdr.Read_hzip_int(1);
			byte capt_tid = (byte)(rdr.Read_byte() - gplx.core.encoders.Base85_.A7_offset);
			byte[] capt_bry = rdr.Read_bry_to();
			Xoh_data_itm img_data = hctx.Pool_mgr__data().Get_by_tid(Xoh_hzip_dict_.Tid__img);
			Xoh_hzip_wkr img_hzip = hctx.Pool_mgr__hzip().Mw__img();
			img_hzip.Decode1(bfr, hdoc_wkr, hctx, hpg, rdr, src, src_bgn, src_end, img_data);
			itm_wtr.Init(true, uid, li_w, div_1_w, div_2_margin, capt_tid, capt_bry);
			itm_wtr.Img_wtr().Init_by_decode(hpg, hctx, src, img_data);
			img_data.Pool__rls();
			img_hzip.Pool__rls();
		}			
		grp_wtr.Init(hctx.Mode_is_diff(), uid, cls_bry, ul_w, xtra_cls_bry, xtra_style_bry, xtra_atr_bry, itm_ary);
		grp_wtr.Bfr_arg__add(bfr);
	}
	public void				Pool__rls	() {pool_mgr.Rls_fast(pool_idx);} private Gfo_poolable_mgr pool_mgr; private int pool_idx;
	public Gfo_poolable_itm	Pool__make	(Gfo_poolable_mgr mgr, int idx, Object[] args) {Xoh_gly_hzip rv = new Xoh_gly_hzip(); rv.pool_mgr = mgr; rv.pool_idx = idx; rv.hook = (byte[])args[0]; return rv;}
	private final Int_flag_bldr flag_bldr = new Int_flag_bldr().Pow_ary_bld_(1, 1, 1, 3);
	private static final int // SERIALIZED
	  Flag__ul__xtra_atr		=  0
	, Flag__ul__xtra_cls		=  1
	, Flag__ul__xtra_style		=  2
	, Flag__gly_tid				=  3
	;
}
