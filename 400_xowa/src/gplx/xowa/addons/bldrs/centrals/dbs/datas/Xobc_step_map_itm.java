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
package gplx.xowa.addons.bldrs.centrals.dbs.datas; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*; import gplx.xowa.addons.bldrs.centrals.dbs.*;
public class Xobc_step_map_itm {
	public Xobc_step_map_itm(int sm_id, int task_id, int step_id, int step_seqn) {
		this.Sm_id = sm_id;
		this.Task_id = task_id;
		this.Step_id = step_id;
		this.Step_seqn = step_seqn;
	}
	public final    int Sm_id;
	public final    int Task_id;
	public final    int Step_id;
	public final    int Step_seqn;
}
