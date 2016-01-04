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
package gplx.xowa.htmls.core.wkrs.thms; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import gplx.core.primitives.*; import gplx.core.brys.*; import gplx.core.brys.fmtrs.*; import gplx.core.brys.args.*;
import gplx.langs.htmls.*; import gplx.langs.htmls.docs.*;
import gplx.xowa.htmls.core.wkrs.bfr_args.*; import gplx.xowa.htmls.core.wkrs.imgs.*; import gplx.xowa.htmls.core.wkrs.imgs.atrs.*;
public class Xoh_thm_wtr implements gplx.core.brys.Bfr_arg {
	private final Bfr_arg__bry			div_0_align = Bfr_arg__bry.New_empty();
	private final Bfr_arg__int			div_1_width = new Bfr_arg__int(-1);
	private final Bfr_arg__hatr_id		div_1_id = Bfr_arg__hatr_id.New("xothm_");
	private final Bfr_arg__hatr_bry		div_2_href = new Bfr_arg__hatr_bry(Gfh_atr_.Bry__href);
	private final Bfr_arg__bry_ary		div_2_magnify = new Bfr_arg__bry_ary();
	private final Bfr_arg__bry			div_2_alt = Bfr_arg__bry.New(Bry_.Empty);
	private final Bry_bfr tmp_bfr = Bry_bfr.new_(255);
	private Bfr_arg div_1_img = Bfr_arg_.Noop, div_2_capt_arg = Bfr_arg_.Noop, div_2_capt_moved_by_tidy_arg = Bfr_arg_.Noop;
	private byte[] img_is_vid_nl, trailing_space;
	public Xoh_thm_wtr Div_2_alt_(boolean v, byte[] img_alt_bry) {
		if (v) {
			alt_fmtr.Bld_bfr_many(tmp_bfr, img_alt_bry);
			div_2_alt.Set_by_val(tmp_bfr.To_bry_and_clear());
		}
		else
			div_2_alt.Set_by_val(Bry_.Empty);
		return this;
	}
	public Xoh_thm_wtr Clear() {
		Bfr_arg_.Clear(div_0_align, div_1_id, div_2_href, div_2_alt); // , div_1_width, div_2_magnify
		div_1_img = div_2_capt_arg = div_2_capt_moved_by_tidy_arg = Bfr_arg_.Noop;
		img_is_vid_nl = Bry_.Empty;
		return this;
	}
	public void Write(Bry_bfr bfr, Xoh_page hpg, Xoh_hdoc_ctx hctx, byte[] src, boolean img_is_vid, int div_0_align, int div_1_w, boolean div_2_alt_exists, byte[] img_alt, Xoh_img_wtr img_wtr
		, byte[] div_2_href, Bfr_arg div_2_capt, boolean div_2_capt_moved_by_tidy) {
		this.Clear();
		this.img_is_vid_nl = img_is_vid ? Byte_ascii.Nl_bry : Bry_.Empty;
		this.trailing_space = img_is_vid ? Bry_.Empty : Byte_ascii.Space_bry;
		this.div_0_align.Set_by_val(gplx.xowa.parsers.lnkis.Xop_lnki_align_h_.To_bry(div_0_align));
		if (!hctx.Mode_is_diff())
			this.div_1_id.Set(img_wtr.Fsdb_itm().Html_uid());
		this.div_1_width.Set(div_1_w);
		this.div_1_img = img_wtr;
		this.div_2_href.Set_by_bry(div_2_href);
		div_2_magnify.Set(hctx.Fsys__root(), bry_div_2_magnify);
		if (div_2_capt_moved_by_tidy) {
			this.div_2_capt_moved_by_tidy_arg = div_2_capt;
			this.div_2_capt_arg = Bfr_arg_.Noop;
		}
		else {
			this.div_2_capt_moved_by_tidy_arg = Bfr_arg_.Noop;
			this.div_2_capt_arg = div_2_capt;
		}
		this.Div_2_alt_(div_2_alt_exists, img_alt);
		this.Bfr_arg__add(bfr);
	}
	public void Bfr_arg__add(Bry_bfr bfr) {
		fmtr.Bld_bfr_many(bfr, div_0_align, div_1_id, div_1_width, img_is_vid_nl, div_1_img, trailing_space, div_2_href, div_2_magnify, div_2_capt_arg, div_2_capt_moved_by_tidy_arg, div_2_alt);
	}
	private static final Bry_fmtr fmtr = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( "<div class=\"thumb t~{div_0_align}\">"
	,   "<div~{div_1_id} class=\"thumbinner\" style=\"width:~{div_1_width}px;\">~{img_is_vid_nl}~{div_1_img}~{trailing_space}"	// NOTE: trailing space is intentional; matches jtidy behavior
	,     "<div class=\"thumbcaption\">"
	,       "<div class=\"magnify\"><a~{div_2_href} class=\"internal\" title=\"Enlarge\"></a></div>"
	,       "~{div_2_capt}</div>~{div_2_capt_moved_by_tidy}~{div_2_alt}"
	,   "</div>"
	, "</div>"
	), "div_0_align", "div_1_id", "div_1_width", "img_is_vid_nl", "div_1_img", "trailing_space", "div_2_href", "div_2_magnify", "div_2_capt", "div_2_capt_moved_by_tidy", "div_2_alt");
	private static final Bry_fmtr alt_fmtr = Bry_fmtr.new_(String_.Concat_lines_nl_skip_last
	( ""
	,     "<hr>"
	,     "<div class=\"thumbcaption\">~{alt}</div>"
	), "alt");
	private static final byte[] bry_div_2_magnify = Bry_.new_a7("bin/any/xowa/file/mediawiki.file/magnify-clip.png");
}
