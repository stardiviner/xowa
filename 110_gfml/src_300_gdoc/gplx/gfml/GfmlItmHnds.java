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
package gplx.gfml; import gplx.*;
public class GfmlItmHnds {
	public int Count() {return list.Count();} List_adp list = List_adp_.New();
	public GfmlNde Get_at(int idx) {return (GfmlNde)list.Get_at(idx);}
	public void Add(GfmlNde nde) {list.Add(nde);}		
	public static GfmlItmHnds new_() {return new GfmlItmHnds();} GfmlItmHnds() {}
}
