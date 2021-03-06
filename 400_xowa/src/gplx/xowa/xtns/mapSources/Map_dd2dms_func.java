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
package gplx.xowa.xtns.mapSources; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.primitives.*;
import gplx.xowa.xtns.pfuncs.*;
import gplx.xowa.langs.*; import gplx.xowa.langs.kwds.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.tmpls.*;
public class Map_dd2dms_func extends Pf_func_base {
	@Override public int Id() {return Xol_kwd_grp_.Id_mapSources_dd2dms;}
	@Override public Pf_func New(int id, byte[] name) {return new Map_dd2dms_func().Name_(name);}
	@Override public void Func_evaluate(Bry_bfr bfr, Xop_ctx ctx, Xot_invk caller, Xot_invk self, byte[] src) {
		byte[] coord = Eval_argx(ctx, src, caller, self);
		int args_len = self.Args_len();
		Bry_bfr tmp_bfr = ctx.Wiki().Utl__bfr_mkr().Get_b128();
		byte[] plus = Bry_.Empty, minus = Bry_.Empty;
		int prec = 4;
		Xop_func_arg_itm func_arg = new Xop_func_arg_itm();
		for (int i = 0; i < args_len; i++) {
			Arg_nde_tkn arg = self.Args_get_by_idx(i);
			func_arg.Set(tmp_bfr, ctx, src, caller, self, arg);
			byte[] key = func_arg.key;
			Object key_tid_obj = Key_hash.Get_by(key);
			if (key_tid_obj != null) {
				byte[] val = func_arg.val;
				switch (((Byte_obj_val)key_tid_obj).Val()) {
					case Key_tid_plus:		plus = val; break;
					case Key_tid_minus:		minus = val; break;
					case Key_tid_precision:	prec = Bry_.To_int_or(val, prec); break;
				}
			}
		}
		tmp_bfr.Mkr_rls();
		Map_math map_math = Map_math.Instance;
		if (map_math.Ctor(coord, prec, Bry_.Empty, 2))
			bfr.Add(map_math.Get_dms(plus, minus));
		else
			map_math.Fail(ctx, src, self, bfr, this.Name());
	}
	public static void Deg_to_dms(Bry_bfr bfr, boolean coord_is_lng, byte[] coord, int prec) {
		Map_math map_math = Map_math.Instance;
		if (map_math.Ctor(coord, prec, Bry_.Empty, 2)) {
			bfr.Add(map_math.Get_dms(Bry_.Empty, Bry_.Empty));
			byte[] dir = coord_is_lng ? map_math.Coord_dir_ns() : map_math.Coord_dir_ew();
			bfr.Add_byte_space().Add(dir);
		}
	}
	public static final Map_dd2dms_func Instance = new Map_dd2dms_func(); Map_dd2dms_func() {}
	private static final byte Key_tid_plus = 1, Key_tid_minus = 2, Key_tid_precision = 3;
	private static final Hash_adp_bry Key_hash = Hash_adp_bry.cs()
	.Add_str_byte("plus"		, Key_tid_plus)
	.Add_str_byte("minus"		, Key_tid_minus)
	.Add_str_byte("precision"	, Key_tid_precision)
	;
}
