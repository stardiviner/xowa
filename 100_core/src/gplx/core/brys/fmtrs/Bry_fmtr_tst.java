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
package gplx.core.brys.fmtrs; import gplx.*; import gplx.core.*; import gplx.core.brys.*;
import org.junit.*;
public class Bry_fmtr_tst {
	private final Bry_fmtr_fxt fxt = new Bry_fmtr_fxt();
	@Test  public void Text()			{fxt.Clear().Fmt("a").Test("a");}
	@Test  public void Idx__1()			{fxt.Clear().Fmt("~{0}").Args("a").Test("a");}
	@Test  public void Idx__3()			{fxt.Clear().Fmt("~{0}~{1}~{2}").Args("a", "b", "c").Test("abc");}
	@Test  public void Idx__mix()		{fxt.Clear().Fmt("a~{0}c~{1}e").Args("b", "d").Test("abcde");}
	@Test  public void Idx__missing()	{fxt.Clear().Fmt("~{0}").Test("~{0}");}

	@Test  public void Key__basic() 	{fxt.Clear().Fmt("~{key}").Keys("key").Args("a").Test("a");}
	@Test  public void Key__mult()		{fxt.Clear().Fmt("~{key1}~{key2}").Keys("key1", "key2").Args("a", "b").Test("ab");}
	@Test  public void Key__repeat()	{fxt.Clear().Fmt("~{key1}~{key1}").Keys("key1").Args("a").Test("aa");}

	@Test  public void Mix()			{fxt.Clear().Fmt("~{key1}~{1}").Keys("key1", "key2").Args("a", "b").Test("ab");}

	@Test  public void Simple() {
		fxt.Clear().Fmt("0~{key1}1~{key2}2").Keys("key1", "key2").Args(".", ",").Test("0.1,2");
	}
	@Test  public void Cmd() {
		Bry_fmtr_tst_mok mok = new Bry_fmtr_tst_mok();
		Bry_fmtr fmtr = Bry_fmtr.new_("0~{key1}2~{<>3<>}4", "key1").Eval_mgr_(mok);
		Tfds.Eq("012~{<>3<>}4", fmtr.Bld_str_many("1"));
		mok.Enabled_(true);
		Tfds.Eq("01234", fmtr.Bld_str_many("1"));
	}
	@Test  public void Bld_bfr_many_and_set_fmt() {
		fxt.Bld_bfr_many_and_set_fmt("a~{0}c", Object_.Ary("b"), "abc");
	}
	@Test  public void Escape_tilde() {
		Tfds.Eq("~~~~~~", Bry_fmtr.Escape_tilde("~~~"));
	}
}
class Bry_fmtr_tst_mok implements Bry_fmtr_eval_mgr {
	public boolean Enabled() {return enabled;} public void Enabled_(boolean v) {enabled = v;} private boolean enabled;
	public byte[] Eval(byte[] cmd) {
		return enabled ? cmd : null;
	}
}
class Bry_fmtr_fxt {
	private final Bry_fmtr fmtr = Bry_fmtr.new_();
	private final Bry_bfr bfr = Bry_bfr.new_();
	private Object[] args;
	public Bry_fmtr_fxt Clear() {fmtr.Fmt_(String_.Empty).Keys_(String_.Empty); args = Object_.Ary_empty; return this;}
	public Bry_fmtr_fxt Fmt	(String fmt) {fmtr.Fmt_(fmt); return this;}
	public Bry_fmtr_fxt Keys(String... args) {fmtr.Keys_(args); return this;}
	public Bry_fmtr_fxt Args(Object... args) {this.args = args; return this;}
	public void Test(String expd) {
		fmtr.Bld_bfr_many(bfr, args);
		Tfds.Eq(expd, bfr.To_str_and_clear());
	}
	public void Bld_bfr_many_and_set_fmt(String fmt, Object[] args, String expd) {
		fmtr.Fmt_(fmt);
		fmtr.Bld_bfr_many_and_set_fmt(args);
		Tfds.Eq(expd, String_.new_a7(fmtr.Fmt()));
	}
}
