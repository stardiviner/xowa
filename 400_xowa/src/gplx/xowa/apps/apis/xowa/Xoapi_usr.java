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
package gplx.xowa.apps.apis.xowa; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*;
import gplx.xowa.apps.apis.xowa.usrs.*;
public class Xoapi_usr implements Gfo_invk {
	public void Ctor_by_app(Xoae_app app) {
		bookmarks.Ctor_by_app(app);
		history.Ctor_by_app(app);
	}
	public void Init_by_app(Xoa_app app) {
	}
	public void Init_by_kit(Xoae_app app) {
		bookmarks.Init_by_kit(app);
		history.Init_by_kit(app);
	}
	public Xoapi_bookmarks	Bookmarks() {return bookmarks;} private final    Xoapi_bookmarks bookmarks = new Xoapi_bookmarks();
	public Xoapi_history	History()	{return history;}	private final    Xoapi_history history = new Xoapi_history();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_bookmarks)) 							return bookmarks;
		else if	(ctx.Match(k, Invk_history)) 							return history;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_bookmarks = "bookmarks", Invk_history = "history";
}
