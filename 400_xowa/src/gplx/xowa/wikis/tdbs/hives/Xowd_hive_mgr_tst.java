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
package gplx.xowa.wikis.tdbs.hives; import gplx.*; import gplx.xowa.*; import gplx.xowa.wikis.*; import gplx.xowa.wikis.tdbs.*;
import org.junit.*;
import gplx.xowa.wikis.nss.*;
public class Xowd_hive_mgr_tst {
	Xowd_hive_mgr_fxt fxt = new Xowd_hive_mgr_fxt();
	@Before public void init() {fxt.Clear();}
	@Test  public void Create() {
		fxt.Create("A", "A|A data\n")
			.Tst_reg(String_.Concat_lines_nl("0|A|A|1"))
			.Tst_fil(0, String_.Concat("!!!!*|\n", "A|A data\n"))
			;
	}
	@Test  public void Create_2() {
		fxt	.Create("A", "A|1\n")
			.Create("B", "B|12\n")
			.Tst_reg(String_.Concat_lines_nl("0|A|B|2"))
			.Tst_fil(0, String_.Concat("!!!!%|!!!!&|\n", "A|1\n", "B|12\n"))
			;
	}
	@Test  public void Create_3() {
		fxt	.Create("A", "A|1\n")
			.Create("B", "B|12\n")
			.Create("C", "C|123\n")
			.Tst_reg(String_.Concat_lines_nl("0|A|C|3"))
			.Tst_fil(0, String_.Concat("!!!!%|!!!!&|!!!!'|\n", "A|1\n", "B|12\n", "C|123\n"))
			;
	}
	@Test  public void Create_sort() {
		fxt	.Create_and_sort("C", "C|1\n")
			.Create_and_sort("A", "A|12\n")
			.Create_and_sort("B", "B|123\n")
			.Tst_reg(String_.Concat_lines_nl("0|A|C|3"))
			.Tst_fil(0, String_.Concat("!!!!&|!!!!'|!!!!%|\n", "A|12\n", "B|123\n", "C|1\n"))
			;
	}
	@Test  public void Update() {
		fxt	.Create("A", "A|A data\n")
			.Create("B", "B|B data\n")
			.Create("C", "C|C data\n")
			.Tst_reg(String_.Concat_lines_nl("0|A|C|3"))
			.Tst_fil(0, String_.Concat("!!!!*|!!!!*|!!!!*|\n", "A|A data\n", "B|B data\n", "C|C data\n"))
			.Update("B", "B|changed\n")
			.Tst_reg(String_.Concat_lines_nl("0|A|C|3"))
			.Tst_fil(0, String_.Concat("!!!!*|!!!!+|!!!!*|\n", "A|A data\n", "B|changed\n", "C|C data\n"))
			;
	}
}
class Xowd_hive_mgr_fxt {
	Xoae_app app; Xowe_wiki wiki; Xowd_hive_mgr mgr;
	public void Clear() {
		app = Xoa_app_fxt.Make__app__edit();
		wiki = Xoa_app_fxt.Make__wiki__edit(app);
		mgr = new Xowd_hive_mgr(wiki, Xotdb_dir_info_.Tid_page);
	}
	public Xowd_hive_mgr_fxt Tst_reg(String expd) {
		Io_url file_orig = Io_url_.mem_fil_("mem/xowa/wiki/en.wikipedia.org/ns/000/title/reg.csv");
		Tfds.Eq_str_lines(expd, Io_mgr.Instance.LoadFilStr(file_orig));
		return this;
	}
	public Xowd_hive_mgr_fxt Tst_fil(int fil, String expd) {
		Io_url url = wiki.Tdb_fsys_mgr().Url_ns_fil(Xotdb_dir_info_.Tid_page, Xow_ns_.Tid__main, fil);
		Tfds.Eq_str_lines(expd, Io_mgr.Instance.LoadFilStr(url));
		return this;
	}
	public Xowd_hive_mgr_fxt Update(String key, String data) {
		mgr.Update(wiki.Ns_mgr().Ns_main(), Bry_.new_a7(key), null, Bry_.new_a7(data), 0, Byte_ascii.Pipe, true, true);
		return this;
	}
	public Xowd_hive_mgr_fxt Create(String key, String data) {
		mgr.Create(wiki.Ns_mgr().Ns_main(), Bry_.new_a7(key), Bry_.new_a7(data), null);		
		return this;
	}
	public Xowd_hive_mgr_fxt Create_and_sort(String key, String data) {
		mgr.Create(wiki.Ns_mgr().Ns_main(), Bry_.new_a7(key), Bry_.new_a7(data), new Bry_comparer_bgn_eos(0));		
		return this;
	}
//	public void Get(String ttl_str, boolean exists) {
//		Xoa_ttl ttl = Xoa_ttl.Parse(wiki, Bry_.new_u8(ttl_str));
//		Xowd_page_itm row = mgr.Get(ttl.Ns(), ttl.Full_txt());
//		Tfds.Eq(exists, row != null);
//	}
}
