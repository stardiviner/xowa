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
package gplx.xowa.mediawiki.includes.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.mediawiki.*; import gplx.xowa.mediawiki.includes.*;
import org.junit.*; import gplx.core.tests.*;
import gplx.xowa.mediawiki.includes.linkers.*;
public class Xomw_link_holders__tst {
	private final    Xomw_link_holders__fxt fxt = new Xomw_link_holders__fxt();
	@Test   public void Replace__basic() {
		fxt.Init__add("A", "a");
		fxt.Test__replace("a <!--LINK 0--> b", "a <a href='/wiki/A' title='A'>a</a> b");
	}
}
class Xomw_link_holders__fxt {
	private final    Xomw_link_holders holders = new Xomw_link_holders(new Xomw_link_renderer(new Xomw_sanitizer()), Bry_bfr_.New());
	private final    Xomw_parser_bfr pbfr = new Xomw_parser_bfr();
	private boolean apos = true;
	public Xomw_link_holders__fxt() {
	}
	public void Init__add(String ttl, String capt) {
		holders.Test__add(Xomw_Title.newFromText(Bry_.new_u8(ttl)), Bry_.new_u8(capt));
	}
	public void Test__replace(String src, String expd) {
		if (apos) expd = gplx.langs.htmls.Gfh_utl.Replace_apos(expd);
		holders.Replace(new Xomw_parser_ctx(), pbfr.Init(Bry_.new_u8(src)));
		Gftest.Eq__str(expd, pbfr.Rslt().To_str_and_clear());
	}
}
