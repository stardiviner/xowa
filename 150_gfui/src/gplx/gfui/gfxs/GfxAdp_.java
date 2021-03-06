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
package gplx.gfui.gfxs; import gplx.*; import gplx.gfui.*;
import java.awt.Graphics2D;
import gplx.gfui.imgs.*;
public class GfxAdp_ {
	@gplx.Internal protected static GfxAdp new_(Graphics2D graphics) {return GfxAdpBase.new_(graphics);}	
	public static GfxAdp image_(ImageAdp image) {
				Graphics2D graphics = (Graphics2D)((java.awt.Image)image.Under()).getGraphics();
				return GfxAdpBase.new_(graphics);
	}
}
