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
package gplx.xowa.hdumps; import gplx.*; import gplx.xowa.*;
import org.junit.*; import gplx.xowa.files.*;
import gplx.xowa.hdumps.core.*; import gplx.xowa.hdumps.dbs.*; import gplx.xowa.hdumps.pages.*; import gplx.xowa.xtns.hieros.*;
public class Xodb_hdump_mgr__write_tst {
	@Before public void init() {fxt.Clear();} private Xodb_hdump_mgr__write_fxt fxt = new Xodb_hdump_mgr__write_fxt();
	@Test   public void Image_full() {
		fxt.Expd_itms_xfers(fxt.Make_xfer(0, 0, 0, "A.png", "trg/orig/7/0/A.png"));
		fxt.Test_write_all
		( "[[File:A.png|test_caption]]"
		, "<a href=\"/wiki/File:A.png\" class=\"image\" xowa_title=\"A.png\"><img id=\"xowa_file_img_0\" alt=\"test_caption\" xowa_img='0' /></a>");
	}
	@Test   public void Image_thumb() {
		fxt.Expd_itms_xfers(fxt.Make_xfer(0, 0, 0, "A.png", "trg/thumb/7/0/A.png/220px.png"));
		fxt.Test_write_all
		( "[[File:A.png|thumb|test_caption]]", String_.Concat_lines_nl_skip_last
		( "<div class=\"thumb tright\">"
		, "  <div id=\"xowa_file_div_0\" class=\"thumbinner\" xowa_img_style='0'>"
		, "    <a href=\"/wiki/File:A.png\" class=\"image\" xowa_title=\"A.png\"><img id=\"xowa_file_img_0\" alt=\"\" xowa_img='0' /></a>"
		, "    <div class=\"thumbcaption\"><xowa_mgnf id='0'/>"
		, "      test_caption"
		, "    </div>"
		, "  </div>"
		, "</div>"
		));
	}
	@Test   public void Audio_thumb() {
		fxt.Expd_itms_xfers(fxt.Make_xfer(0, 220, -1, "A.oga", ""));
		fxt.Test_write_all
		( "[[File:A.oga|thumb|test_caption]]", String_.Concat_lines_nl_skip_last
		( "<div class=\"thumb tright\">"
		, "  <div id=\"xowa_file_div_0\" class=\"thumbinner\" xowa_img_style='0'>"
		, "    <div id=\"xowa_media_div\"><xowa_play id='0'/><xowa_info id='0'/>"
		, "    </div>"
		, "    <div class=\"thumbcaption\"><xowa_mgnf id='0'/>"
		, "      test_caption"
		, "    </div>"
		, "  </div>"
		, "</div>"
		));
	}
	@Test   public void Video_thumb() {
		fxt.Expd_itms_xfers(fxt.Make_xfer(0, 0, 0, "A.ogv", ""));
		fxt.Test_write_all
		( "[[File:A.ogv|thumb|test_caption]]", String_.Concat_lines_nl_skip_last
		( "<div class=\"thumb tright\">"
		, "  <div id=\"xowa_file_div_0\" class=\"thumbinner\" xowa_img_style='0'>"
		, "    <div id=\"xowa_media_div\">"
		, "      <div>"
		, "        <a href=\"/wiki/File:A.ogv\" class=\"image\" title=\"A.ogv\">"
		, "          <img id=\"xowa_file_img_0\" xowa_img='0' alt=\"\" />"
		, "        </a>"
		, "      </div><xowa_play id='0'/>"
		, "    </div>"
		, "    <div class=\"thumbcaption\"><xowa_mgnf id='0'/>"
		, "      test_caption"
		, "    </div>"
		, "  </div>"
		, "</div>"
		));
	}
	@Test   public void Hiero() {
		Hiero_html_mgr_fxt hiero_fxt = new Hiero_html_mgr_fxt(fxt.Fxt());
		hiero_fxt.Reset();
		hiero_fxt.Init_hiero_A1_B1();
		fxt.Test_write_frag("<hiero>A1</hiero>", "src='~{xowa_hiero_dir}hiero_A1.png'");
	}
	@Test   public void Gallery() {
		fxt.Test_write_all
		( "<gallery>File:A.png|A1</gallery>", String_.Concat_lines_nl_skip_last
		( "<ul id=\"xowa_gallery_ul_0\" class=\"gallery mw-gallery-traditional\" xowa_gly_box_max='0'>"
		, "  <li id=\"xowa_gallery_li_0\" class=\"gallerybox\" xowa_gly_box_w='0'>"
		, "    <div xowa_gly_box_w='0'>"
		, "      <div class=\"thumb\" style=\"height: 150px;\">A.png</div>"
		, "      <div class=\"gallerytext\"><p>A1"
		, "</p>"
		, ""
		, "      </div>"
		, "    </div>"
		, "  </li>"
		, "</ul>"
		));
	}
}
class Xodb_hdump_mgr__base_fxt {
	protected Xodb_hdump_mgr hdump_mgr;
	protected Bry_bfr bfr = Bry_bfr.reset_(255);
	protected Xow_wiki wiki; protected Xoa_page page;
	public Xop_fxt Fxt() {return fxt;} protected Xop_fxt fxt;
	public void Clear() {
		if (fxt == null) {
			fxt = new Xop_fxt();
			wiki = fxt.Wiki();
			page = wiki.Ctx().Cur_page();
			hdump_mgr = new Xodb_hdump_mgr(wiki);
		}
		fxt.Reset();
		page.Revision_data().Id_(0);
		this.Clear_end();
 		}
	@gplx.Virtual public void Clear_end() {}
	public void Exec_write(String raw) {
		Xop_root_tkn root = fxt.Exec_parse_page_all_as_root(Bry_.new_utf8_(raw));
		page.Root_(root);
		hdump_mgr.Write(bfr, page);
	}
}
class Xodb_hdump_mgr__write_fxt extends Xodb_hdump_mgr__base_fxt {
	private ListAdp expd_itms_xfers = ListAdp_.new_();
	@Override public void Clear_end() {expd_itms_xfers.Clear();}
	public Hdump_data_img__base Make_xfer(int uid, int img_w, int img_h, String lnki_ttl, String img_src) {return new Hdump_data_img__basic().Init_by_base(uid, img_w, img_h, Bry_.new_utf8_(lnki_ttl), Bry_.new_utf8_(img_src));}
	public void Expd_itms_xfers(Hdump_data_img__base... itms) {expd_itms_xfers.AddMany((Object[])itms);}
	public void Test_write_all (String raw, String expd_html) {Test_write(Bool_.N, raw, expd_html);}
	public void Test_write_frag(String raw, String expd_frag) {Test_write(Bool_.Y, raw, expd_frag);}
	public void Test_write(boolean frag, String raw, String expd_html) {
		this.Exec_write(raw);
		String actl_html = String_.new_utf8_(page.Hdump_data().Body());
		if (frag)
			Tfds.Eq_true(String_.Has(actl_html, expd_html), actl_html);
		else
			Tfds.Eq_str_lines(expd_html, actl_html);
		if (expd_itms_xfers.Count() > 0) Tfds.Eq_ary_str(Xfer_to_str_ary(expd_itms_xfers), Xfer_to_str_ary(page.Hdump_data().Data()));
	}
	private static String[] Xfer_to_str_ary(ListAdp list) {
		int len = list.Count();
		String[] rv = new String[len];
		for (int i = 0; i < len; ++i) {
			Hdump_data_img__base itm = (Hdump_data_img__base)list.FetchAt(i);
			rv[i] = itm.Data_print();
		}
		return rv;
	}
}
