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
package gplx.xowa.apps.apis.xowa.bldrs; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.apis.xowa.bldrs.filters.*;
import gplx.xowa.apps.apis.xowa.bldrs.imports.*;
import gplx.xowa.apps.apis.xowa.bldrs.runners.*;
public class Xoapi_bldr_wiki implements Gfo_invk {
	public void Ctor_by_app(Xoa_app app) {
		runner.Ctor_by_app(app);
	}
	public Xoapi_import Import() {return import_api;} private final    Xoapi_import import_api = new Xoapi_import();
	public Xoapi_runner Runner() {return runner;} private final    Xoapi_runner runner = new Xoapi_runner();
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if		(ctx.Match(k, Invk_import)) 						return import_api;
		else if	(ctx.Match(k, Invk_runner)) 						return runner;
		else	return Gfo_invk_.Rv_unhandled;
	}
	private static final String Invk_import = "import", Invk_runner = "runner";
}
