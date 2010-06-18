package org.nerditest.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	private final AnswerSvcAsync service = GWT.create(AnswerSvc.class);
	public String [] teams = {"mega","giga","tera","peta","exa","zetta","yotta","xona","weka","vunda"};
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
						service.getGroupsResults(teams,new AsyncCallback<List<TeamResult>>() {

							@Override
							public void onSuccess(List<TeamResult> result) {
								AbstractDataTable z = createTable(result);
								pie.draw(z, createOptions());

							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}
						}
						);
						
					}
				};
				b.scheduleRepeating(1000);

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
	private AbstractDataTable createTable(List<TeamResult> result) {

		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Label");
		for (String team : teams) {
			data.addColumn(ColumnType.NUMBER, team);
		}
		data.addRows(10);
		int i = 0;
		for (String team : teams) {
			data.setValue(i, 0, team);
			data.setValue(i, i+1, result.get(i).getTeamResult());
			i ++;
		}
	
		return data;
	}
	private AbstractDataTable createTable() {

		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Label");
		for (String team : teams) {
			data.addColumn(ColumnType.NUMBER, team);
		}
		data.addRows(10);
		int i = 0;
		for (String team : teams) {
			data.setValue(i, 0, team);
			data.setValue(i, i+1, 0);
			i ++;
		}		
		return data;
	}
}
