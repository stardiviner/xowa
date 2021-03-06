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
package gplx.xowa.htmls.core.wkrs.addons.gallerys; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*; import gplx.xowa.htmls.core.wkrs.addons.*;
import org.junit.*; import gplx.core.tests.*; import gplx.xowa.htmls.core.makes.tests.*;
public class Xoh_gallery_hzip_tst {
	private final    Xoh_hzip_fxt fxt = new Xoh_hzip_fxt().Init_mode_diff_y_();
	@Test   public void Basic() {
		fxt.Test__decode
		( "<ul class=\"gallery mw-gallery-traditional style=\"max-width:489px; _width:489px;\">"
		, "<ul class=\"gallery mw-gallery-traditional style=\"max-width:489px; _width:489px;\">"
		);
		Gftest.Eq__bool_y(fxt.Page().Xtn__gallery_exists());
	}
}
