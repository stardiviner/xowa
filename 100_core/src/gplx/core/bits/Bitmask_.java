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
package gplx.core.bits; import gplx.*; import gplx.core.*;
public class Bitmask_ {
	public static boolean		Has_int(int val, int find)	{return find == (val & find);}
	public static int		Flip_int(boolean enable, int val, int find) {
		boolean has = find == (val & find);
		return (has ^ enable) ? val ^ find : val;
	}
	public static int		Add_int(int lhs, int rhs)	{return lhs | rhs;}
	public static int		Add_int_ary(int... ary) {
		int rv = 0;
		int len = ary.length;
		for (int i = 0; i < len; ++i) {
			int itm = ary[i];
			if (rv == 0)
				rv = itm;
			else
				rv = Flip_int(true, rv, itm);
		}
		return rv;
	}
	public static boolean		Has_byte(byte val, byte find)	{return find == (val & find);}
	public static byte		Add_byte(byte flag, byte itm)	{return (byte)(flag | itm);}
}
