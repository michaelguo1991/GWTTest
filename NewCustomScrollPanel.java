package com.augmentum.client;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.CustomScrollPanel;
import com.google.gwt.user.client.ui.VerticalScrollbar;

public class NewCustomScrollPanel extends CustomScrollPanel{
	
	@Override
	public void setVerticalScrollbar(final VerticalScrollbar scrollbar, int width) {
		super.setVerticalScrollbar(scrollbar, width);
		if (scrollbar != null && scrollbar instanceof CustomVerticalScrollbar) {
			((CustomVerticalScrollbar) scrollbar).addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(MouseOverEvent event) {
					int vPos = scrollbar.getVerticalScrollPosition();
		            int v = getVerticalScrollPosition();
		            if (getVerticalScrollPosition() != vPos) {
		              setVerticalScrollPosition(vPos);
		            }
				}
			});
		}
	}
}
