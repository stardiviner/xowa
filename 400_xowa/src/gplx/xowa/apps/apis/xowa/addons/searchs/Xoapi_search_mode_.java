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
package gplx.xowa.apps.apis.xowa.addons.searchs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*; import gplx.xowa.apps.apis.xowa.addons.*;
public class Xoapi_search_mode_ {
	public static final int Tid__title_full = 0, Tid__title_word = 1;
	public static final String Str__title_full = "title.full", Str__title_word = "title.word"; 
	public static String To_str(int v) {
		switch (v) {
			case Tid__title_full:	return Str__title_full;
			case Tid__title_word:	return Str__title_word;
			default:				throw Err_.new_unhandled_default(v);
		}
	}
	public static int To_int(String v) {
		if		(String_.Eq(v, Str__title_full))	return Tid__title_full;
		else if	(String_.Eq(v, Str__title_word))	return Tid__title_word;
		else										throw Err_.new_unhandled_default(v);
	}
}
