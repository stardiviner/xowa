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
package gplx.xowa.mediawiki.includes.utls; import gplx.*; import gplx.xowa.*; import gplx.xowa.mediawiki.*; import gplx.xowa.mediawiki.includes.*;
import gplx.core.btries.*; import gplx.core.brys.*;
import gplx.core.primitives.*;
public class Php_preg_ {
	public static byte[][] Split(Int_list list, byte[] src, int src_bgn, int src_end, byte[] dlm, boolean extend) {
		// find delimiters
		int dlm_len = dlm.length;
		byte dlm_nth = dlm[dlm_len - 1];
		int i = src_bgn;
		list.Add(src_bgn);
		while (true) {
			if (i == src_end) break;
			int dlm_end = i + dlm_len;
			if (dlm_end <= src_end && Bry_.Eq(src, i, dlm_end, dlm)) {
				if (extend) {
					dlm_end = Bry_find_.Find_fwd_while(src, i, src_end, dlm_nth);
				}
				list.Add(i);
				list.Add(dlm_end);
				i = dlm_end;
			}
			else
				i++;
		}
		list.Add(src_end);

		// create brys
		int rv_len = list.Len() - 1;
		if (rv_len == 1) {
			list.Clear();
			return null;
		}
		if (list.Get_at(list.Len() - 2) == src_end) {	// if 2nd to last elem == src_end, then last item is Bry_.Empty; ignore it; EX: "a''" -> "a", "''" x> "a", "''", ""
			rv_len--;
		}
		byte[][] rv = new byte[rv_len][];
		for (i = 0; i < rv_len; i += 2) {
			rv[i    ] = Bry_.Mid(src, list.Get_at(i + 0), list.Get_at(i + 1));
			if (i + 1 == rv_len) break;
			rv[i + 1] = Bry_.Mid(src, list.Get_at(i + 1), list.Get_at(i + 2));
		}
		list.Clear();
		return rv;
	}
	public static Object Match(Btrie_slim_mgr trie, Btrie_rv trv, byte[] src, int src_bgn, int src_end) {
		trv.Match_bgn = -1;
		int cur = src_bgn;
		while (cur < src_end) {
			byte b = src[cur];
			Object o = trie.Match_at_w_b0(trv, b, src, cur, src_end);
			if (o == null)
				cur += gplx.core.intls.Utf8_.Len_of_char_by_1st_byte(b);
			else {
				trv.Match_bgn = cur;
				return o;
			}
		}
		return null;
	}
	
	public static void Replace(Bry_tmp bry, Bry_bfr tmp, Btrie_slim_mgr find_trie, Btrie_rv trv, byte[] repl_bry) {
		byte[] src = bry.src;
		int src_bgn = bry.src_bgn;
		int src_end = bry.src_end;
 
		int cur = src_bgn;
		int prv = cur;
		boolean dirty = false;

		while (true) {
			// eos
			if (cur == src_end) {
				if (dirty) {
					tmp.Add_mid(src, prv, src_end);
				}
				break;
			}

			byte b = src[cur];
			Object o = find_trie.Match_at_w_b0(trv, b, src, cur, src_end);
			if (o == null) {
				cur += gplx.core.intls.Utf8_.Len_of_char_by_1st_byte(b);
			}
			else {
				dirty = true;
				tmp.Add_mid(src, prv, cur);
				tmp.Add(repl_bry);
				cur = trv.Pos();
				prv = cur;
			}
		}

		if (dirty) {
			bry.Set_by_bfr(tmp);
		}
	}
}
