package org.nerditest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.gwt.visualization.client.visualizations.Gauge;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.Gauge.Options;
public class results {

	public results() {
		
		createChart();
//		    Timer b = new Timer(){
//		    	public void run(){
//		    		createChart();
//		    	}
//		    };
//		    b.scheduleRepeating(5000);
	}
	    
	
	private void createChart(){
		RootPanel.get().clear();
		Runnable onLoadCallback = new Runnable() {
		      public void run() {
		        Panel panel = RootPanel.get();
		 
		        // Create a pie chart visualization.
		        final Gauge pie = new Gauge(createTable(), createOptions());
		       
		        Timer b = new Timer(){
			    	public void run(){
			    		AbstractDataTable z = createTable();
			    		pie.draw(z, createOptions());
			    	}
			    };
			    b.scheduleRepeating(5000);
			    
//		        pie.addSelectHandler(createSelectHandler(pie));
		        panel.add(pie);
		      }
		    };

		    // Load the visualization api, passing the onLoadCallback to be called
		    // when loading is done.
		    VisualizationUtils.loadVisualizationApi(onLoadCallback, Gauge.PACKAGE);
	}
	    private Options createOptions() {
	        Options options = Options.create();
	        options.setWidth(1000);
	        options.setHeight(1000);
	        options.setGaugeRange(0, 100);
	        options.setRedRange(80, 100);
	        options.setYellowRange(60, 80);
	        options.setGreenRange(40, 60);
//	        options.set3D(false);
//	        options.setTitle("My Daily Activities");
//	        String colors[] = new String[]{"red","blue","green","yellow"};
//	        options.setColors(colors);
//	        options.setMin(0);
//	        options.setMax(50);
//	        options.setShowCategories(true);
//	        options.setAxisFontSize(12);
//	        options.setLegendFontSize(14);
//	        options.setTitleFontSize(14);
	        
	        return options;
	      }
	    static int z = 5;
	    private AbstractDataTable createTable() {
	        DataTable data = DataTable.create();
	        data.addColumn(ColumnType.STRING, "Label");
	        data.addColumn(ColumnType.NUMBER, "Team 1");
	        data.addColumn(ColumnType.NUMBER, "Team 2");
	        data.addColumn(ColumnType.NUMBER, "Team 3");
	        data.addColumn(ColumnType.NUMBER, "Team 4");
	        data.addColumn(ColumnType.NUMBER, "Team 5");
	        data.addColumn(ColumnType.NUMBER, "Team 6");
	        data.addColumn(ColumnType.NUMBER, "Team 7");
	        data.addColumn(ColumnType.NUMBER, "Team 8");
	        data.addColumn(ColumnType.NUMBER, "Team 9");
	        data.addColumn(ColumnType.NUMBER, "Team 10");
	        data.addRows(10);
	        data.setValue(0, 0, "Team 1");
	        data.setValue(0, 1, 0 + z);
	        data.setValue(1, 0, "Team 2");
	        data.setValue(1, 2, 0 + z );
	        data.setValue(2, 0, "Team 3");
	        data.setValue(2, 3, 0 + z );
	        data.setValue(3, 0, "Team 4");
	        data.setValue(3, 4, 0 + z );
	        data.setValue(4, 0, "Team 5");
	        data.setValue(4, 5, 0 + z );
	        data.setValue(5, 0, "Teram 6");
	        data.setValue(5, 6, 0 + z );
	        data.setValue(6, 0, "Team 7");
	        data.setValue(6, 7, 0 + z );
	        data.setValue(7, 0, "Team 8");
	        data.setValue(7, 8, 0 + z );
	        data.setValue(8, 0, "Team 9");
	        data.setValue(8, 9, 0 + z );
	        data.setValue(9, 0, "Team 10");
	        data.setValue(9, 10, 0 + z );

	        z+=5;
	        return data;
	      }
}
