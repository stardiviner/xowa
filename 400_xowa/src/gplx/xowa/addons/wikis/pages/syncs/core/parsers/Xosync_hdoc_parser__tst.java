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
package gplx.xowa.addons.wikis.pages.syncs.core.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.pages.*; import gplx.xowa.addons.wikis.pages.syncs.*; import gplx.xowa.addons.wikis.pages.syncs.core.*;
import org.junit.*;
import gplx.langs.htmls.*;
public class Xosync_hdoc_parser__tst {
	@Before public void init() {fxt.Clear();} private final    Xosync_hdoc_parser__fxt fxt = new Xosync_hdoc_parser__fxt();
	@Test   public void Remove_edit() {
		fxt.Exec__parse(Gfh_utl.Replace_apos_concat_lines
		( "<h2><span class='mw-headline' id='Section_1'>Section_1</span>"
		, "<span class='mw-editsection'>"
		, "<span class='mw-editsection-bracket'>[</span><a href='/w/index.php?title=Page_1&amp;action=edit&amp;section=1' title='Edit section: Section_1'>edit</a>"
		, "<span class='mw-editsection-bracket'>]</span>"
		, "</span>"
		, "</h2>"
		)).Test__html(Gfh_utl.Replace_apos_concat_lines
		( "<h2><span class='mw-headline' id='Section_1'>Section_1</span>"
		, ""
		, "</h2>"
		));
	}
//		@Test   public void Smoke() {
//			fxt.Exec__parse(Io_mgr.Instance.LoadFilStr("C:\\xowa\\dev\\wm.updater.src.html"));
//			Io_mgr.Instance.SaveFilBry("C:\\xowa\\dev\\wm.updater.trg.html", fxt.Hdoc().Converted());
//		}
}
