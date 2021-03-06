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
package gplx.core.gfo_ndes; import gplx.*; import gplx.core.*;
import gplx.core.type_xtns.*;
public interface GfoFldList {
	int Count();
	boolean Has(String key);
	int Idx_of(String key);
	GfoFld Get_at(int i);
	GfoFld FetchOrNull(String key);
	GfoFldList Add(String key, ClassXtn c);
	String To_str();
}
