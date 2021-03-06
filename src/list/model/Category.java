//@author A0113672L
package list.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Category implements ICategory {
	private static final String DEFAULT_NAME = "";
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static ICategory defaultCategory = null;
    
    private String name = null;
	private Color color = null;
	private List<ITask> list;
	
	/**
	 * Using Category constructor can cause the application to create
	 * multiple Category objects for the same category name.
	 * Use <code>TaskManager.getCategory(categoryName)</code> to prevent this 
	 * potentially incorrect behavior.
	 */
	public Category() {
	    this.name = DEFAULT_NAME;
	    this.color = DEFAULT_COLOR;
	    list = new ArrayList<ITask>();
	}
	
	@Override
	public Color getColor() {
        return this.color;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ICategory setColor(Color color) {
		if (color == null) {
			color = DEFAULT_COLOR;
		}
		this.color = color;
		return this;
	}

	@Override
	public ICategory setName(String name) {
		this.name = name;
		return this;
	}
	
	public static Color getDefaultColor() {
		return DEFAULT_COLOR;
	}
	
	public static String getDefaultName() {
		return DEFAULT_NAME;
	}
	
	public static ICategory getDefaultCategory() {
	    if (defaultCategory == null) {
	        defaultCategory = new Category().setName(DEFAULT_NAME).setColor(DEFAULT_COLOR);
	    }
	    return defaultCategory;
	}

	@Override
	public List<ITask> getList() {
		return list;
	}

	@Override
	public void setList(List<ITask> list) {
		this.list = list;
	}

    @Override
    public int compareTo(ICategory o) {
        return name.compareToIgnoreCase(o.getName());
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof ICategory)) return false;
        ICategory other = (ICategory) o;
        return name.equalsIgnoreCase(other.getName());
    }

}
