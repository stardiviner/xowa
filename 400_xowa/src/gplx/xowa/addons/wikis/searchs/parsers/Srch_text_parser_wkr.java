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
package gplx.xowa.addons.wikis.searchs.parsers; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.wikis.*; import gplx.xowa.addons.wikis.searchs.*;
public interface Srch_text_parser_wkr {
	void Parse_done(Srch_word_itm word);
}
class Srch_text_parser_wkr__noop implements Srch_text_parser_wkr {
	public void Parse_done(Srch_word_itm word) {}
        public static final    Srch_text_parser_wkr__noop Instance = new Srch_text_parser_wkr__noop(); Srch_text_parser_wkr__noop() {}	// TS.static
}
