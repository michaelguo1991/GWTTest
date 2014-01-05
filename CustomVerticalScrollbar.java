package com.augmentum.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.VerticalScrollbar;
import com.google.gwt.user.client.ui.Widget;

/**
 *String lorem = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";
*		HTMLPanel content = new HTMLPanel(lorem);
*		NewCustomScrollPanel panel = new NewCustomScrollPanel();
*		panel.setWidget(content);
*		panel.addStyleName("bar-panel");
*		
*		CustomVerticalScrollbar verticalBar = new CustomVerticalScrollbar();
*		panel.setVerticalScrollbar(verticalBar, 18);
*		
*		RootPanel.get().add(panel);
*	.bar-panel {
*		width: 300px;
*		height: 100px;
*	}
*	
*	.vertical-bar-container {
*		background: #ccc;
*	}
*	
*	.vertical-bar-inner {
*		position: absolute;
*		background: red;
*		top: 0;
*		border-radius: 20px;
*	}
*/

public class CustomVerticalScrollbar extends FocusPanel implements VerticalScrollbar{
	
	private FocusPanel barInnerPanel;
	private Element barInner;
	private Element barContainer;
	
	private int scrollbarWidth;
	private int scrollHeight;
	private int clientHeight;
	private int barHeight;
	private int barTop;
	private int currentPageY;
	
	
	public CustomVerticalScrollbar() {
		this(17);
	}
	
	public CustomVerticalScrollbar(int scrollbarWidth) {
		this.barInnerPanel = new FocusPanel();
		this.barInnerPanel.addStyleName("vertical-bar-inner");
		this.barInner = this.barInnerPanel.getElement();
		
		setWidget(barInnerPanel);
		this.addStyleName("vertical-bar-container");
		this.scrollbarWidth = scrollbarWidth;
		this.barContainer = getElement();
		
		this.barInnerPanel.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				barInnerPanel.addStyleName("in-scrolling");
				currentPageY = event.getClientY();
			}
		});
		
		this.barInnerPanel.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (hasClassName(barInnerPanel, "in-scrolling")) {
					// adjust scrollbar position
					int delay = event.getClientY() - currentPageY;
					int newBarTop = barTop + delay;
					
					if (newBarTop < 0) {
						barTop = 0;
					} else if (newBarTop > getMaximumVerticalScrollPosition()) {
						barTop = getMaximumVerticalScrollPosition();	
					}
					System.out.println(barTop);
					barInner.getStyle().setTop(barTop, Unit.PX);
				}
			}
		});
		
		this.barInnerPanel.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				barInnerPanel.removeStyleName("in-scrolling");
			}
		});
	}
	
	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	@Override
	public int getMaximumVerticalScrollPosition() {
		return this.clientHeight - this.barHeight;
	}

	@Override
	public int getMinimumVerticalScrollPosition() {
		return 0;
	}

	@Override
	public int getVerticalScrollPosition() {
//		return this.barTop;
		return this.barTop / (this.clientHeight - this.barHeight) * (this.scrollHeight - this.clientHeight);
	}

	/**
	 * 
	 * @param position the scrollTop of the scrollable element
	 */
	public void setVerticalScrollPosition(int position) {
		// barTop / (clientHeight - barHeight) = scrollTop / (scrollHeight - clientHeight)
		this.barTop = (int)(((double)position / (double)(this.scrollHeight - this.clientHeight)) * (this.clientHeight - this.barHeight));
		this.barInner.getStyle().setTop(this.barTop, Unit.PX);
	}

	@Override
	public HandlerRegistration addScrollHandler(ScrollHandler handler) {
		return addHandler(handler, ScrollEvent.getType());
	}

	public int getScrollHeight() {
		return this.scrollHeight;
	}

	/**
	 * @param height the scrollHeight of the scrollabel element
	 */
	public void setScrollHeight(int height) {
		this.scrollHeight = height;
		this.clientHeight = this.barContainer.getClientHeight();
		this.barHeight = this.clientHeight * this.clientHeight / this.scrollHeight;
		
		this.barInner.getStyle().setWidth(this.scrollbarWidth, Unit.PX);
		this.barInner.getStyle().setHeight(this.barHeight, Unit.PX);
	}
	
	private boolean hasClassName(Widget w, String className) {
		return w.getElement().getAttribute("class").indexOf(className) != -1;
	}

}

