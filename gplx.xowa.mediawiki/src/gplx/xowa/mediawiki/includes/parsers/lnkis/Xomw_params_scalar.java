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
package gplx.xowa.mediawiki.includes.parsers.lnkis; import gplx.*; import gplx.xowa.*; import gplx.xowa.mediawiki.*; import gplx.xowa.mediawiki.includes.*; import gplx.xowa.mediawiki.includes.parsers.*;
import gplx.xowa.mediawiki.includes.utls.*;
public class Xomw_params_scalar {
	public int physicalWidth;
	public int physicalHeight;
	public byte[] physicalDimensions;
	public int clientWidth;
	public int clientHeight;
	public byte[] comment;
	public int srcWidth;
	public int srcHeight;
	public byte[] mimeType;
	public byte[] dstPath;
	public byte[] dstUrl;
	public byte[] interlace;
	public Xomw_params_scalar() {
		physicalWidth = physicalHeight = clientWidth = clientHeight = srcWidth = srcHeight = Php_utl_.Null_int;
	}
}
