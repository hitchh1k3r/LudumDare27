package com.hitchh1k3rsguide.ld27.panels;

import com.hitchh1k3rsguide.ld27.Enums.cursors;

public interface IPanel {

	public void onAttach();
	public void onDetach();
	public boolean onKey(int key, boolean isDown);
	public boolean onMouseMove(int x, int y);
	public boolean onMouseClick(int x, int y, int button, boolean isDown);
	public void update(double time);
	public void draw();

}
