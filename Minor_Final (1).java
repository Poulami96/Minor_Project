package org.geotools.tutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionListener;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.Mark;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.Symbolizer;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.factory.Hints;
import org.geotools.filter.text.ecql.ECQL;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.StyleLayer;
import org.geotools.styling.ChannelSelection;
import org.geotools.styling.ContrastEnhancement;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.SLD;
import org.geotools.styling.SelectedChannelType;
import org.geotools.swing.action.SafeAction;
import org.opengis.style.ContrastMethod;

public class Minor_Final extends JFrame implements ActionListener{
	JComboBox<String> sr1;
	JComboBox<String> ds1;
	JLabel sr;
	JLabel ds;
	JButton b1;
	private StyleFactory sf = CommonFactoryFinder.getStyleFactory();
    private FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
    private enum GeomType { POINT, LINE, POLYGON };
    private JMapFrame frame;
    private GridCoverage2DReader reader;
    private static final Color LINE_COLOUR = Color.BLUE;
    private static final Color FILL_COLOUR = Color.CYAN;
    private static final Color SELECTED_COLOUR = Color.YELLOW;
    private static final float OPACITY = 1.0f;
    private static final float LINE_WIDTH = 1.0f;
    private static final float POINT_SIZE = 10.0f;
    String source;
    String destination;
    private String geometryAttributeName;
    private GeomType geometryType;
    SimpleFeatureSource shapefileSource;
    SimpleFeatureSource shapefileSource1;
    SimpleFeatureSource shapefileSource2;
    Filter filter;
    JLabel pic;
    Timer tm;
    int x = 0;
    String[] list = {
    		"C:/Users/Nimit Johri/Desktop/images/img1.jpg",       
    		"C:/Users/Nimit Johri/Desktop/images/img2.jpg",       
    		"C:/Users/Nimit Johri/Desktop/images/img3.jpg",       

    };


public 	Minor_Final()
	{sr=new JLabel("Source");
	ds=new JLabel("Destination");
		sr1=new JComboBox<String>();
		ds1=new JComboBox<String>();
		sr1.addItem("Energy Block");
		sr1.addItem("First Block");
		sr1.addItem("Food Court(FC)");
		sr1.addItem("Food Court(FC)Kitchen");
		sr1.addItem("Fourth Block");
		sr1.addItem("Fifth Block");
		sr1.addItem("Seventh Block");
		sr1.addItem("Third Block");
		sr1.addItem("Eightth Block");
		sr1.addItem("Tenth Block");
		sr1.addItem("Ninth Block");
		sr1.addItem("IT Tower");
		sr1.addItem("Iburpp");
		sr1.addItem("Hostel Food Area");
		sr1.addItem("Non-CIT Labs");
		sr1.addItem("Mechanical Labs");
		sr1.addItem("Research And Development Block");
		sr1.addItem("College Girls Hostel");
		sr1.addItem("College Boys Hostel");
		sr1.addItem("Enrollment Office");
		sr1.addItem("Managemengt Development Center");
		sr1.addItem("Exit gate");
		sr1.addItem("Main Amphitheatre");
		sr1.addItem("Parking area for tatra car,upes fire service");
		sr1.addItem("Gandhi Chowk");
		sr1.addItem("Parking Lane Zone 1");
		sr1.addItem("Parking Area 1");
		sr1.addItem("Sixth Block");
		sr1.addItem("Placement Cell");
		sr1.addItem("Amphitheatre");
		sr1.addItem("Women Empowerment Block");
		sr1.addItem("Library");
		sr1.addItem("Cafeteria");
		sr1.addItem("Parking Lane Zone 2");
		sr1.addItem("Ninth Block Extension 1");
		sr1.addItem("Ninth Block Extension 2");
		sr1.addItem("ONGC Museum");
		sr1.addItem("Mess of college hostel");
		sr1.addItem("Electrical Sub Station");
		sr1.addItem("Entry Gate");
		sr1.addItem("MIG 23 Green Area");
		sr1.addItem("WasteLand Area");
		sr1.addItem("Library Front Green Area");
		sr1.addItem("ONGC Museum Back Green Area");
		sr1.addItem("Playground");
		sr1.addItem("FC Back Green Area");
		sr1.addItem("Research And Development Green Area");
//ds1.setEditable(true);
ds1.addItem("Energy Block");
ds1.addItem("First Block");
ds1.addItem("Food Court(FC)");
ds1.addItem("Food Court(FC)Kitchen");
ds1.addItem("Fourth Block");
ds1.addItem("Fifth Block");
ds1.addItem("Seventh Block");
ds1.addItem("Third Block");
ds1.addItem("Eightth Block");
ds1.addItem("Tenth Block");
ds1.addItem("Ninth Block");
ds1.addItem("IT Tower");
ds1.addItem("Iburpp");
ds1.addItem("Hostel Food Area");
ds1.addItem("Non-CIT Labs");
ds1.addItem("Mechanical Labs");
ds1.addItem("Research And Development Block");
ds1.addItem("College Girls Hostel");
ds1.addItem("College Boys Hostel");
ds1.addItem("Enrollment Office");
ds1.addItem("Managemengt Development Center");
ds1.addItem("Exit gate");
ds1.addItem("Main Amphitheatre");
ds1.addItem("Parking area for tatra car,upes fire service");
ds1.addItem("Gandhi Chowk");
ds1.addItem("Parking Lane Zone 1");
ds1.addItem("Parking Area 1");
ds1.addItem("Sixth Block");
ds1.addItem("Placement Cell");
ds1.addItem("Amphitheatre");
ds1.addItem("Women Empowerment Block");
ds1.addItem("Library");
ds1.addItem("Cafeteria");
ds1.addItem("Parking Lane Zone 2");
ds1.addItem("Ninth Block Extension 1");
ds1.addItem("Ninth Block Extension 2");
ds1.addItem("ONGC Museum");
ds1.addItem("Mess of college hostel");
ds1.addItem("Electrical Sub Station");
ds1.addItem("Entry Gate");
ds1.addItem("MIG 23 Green Area");
ds1.addItem("WasteLand Area");
ds1.addItem("Library Front Green Area");
ds1.addItem("ONGC Museum Back Green Area");
ds1.addItem("Playground");
ds1.addItem("FC Back Green Area");
ds1.addItem("Research And Development Green Area");
b1=new JButton("Calculate Path");
b1.addActionListener(this);
pic = new JLabel();
pic.setBounds(40, 30, 700, 300);



//Call The Function SetImageSize
SetImageSize(2);


//set a timer
tm = new Timer(500,new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        SetImageSize(x);
        x += 1;
        if(x >= list.length )
            x = 0; 
    }
});
add(pic);
tm.start();

	setLayout(null);
    
	add(sr);
	add(sr1);
	add(ds);
	add(ds1);
	add(b1);
	setSize(800, 400);
    getContentPane().setBackground(Color.decode("#CCFFFF"));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
	setTitle("Choose Source & Destination");
	}
	public static void main(String args[]) throws Exception
	{
		Minor_Final q=new Minor_Final();
		}

	private void getLayersAndDisplay() throws Exception {
    	File imageFile = new File("C:/Users/Nimit Johri/Desktop/upes12.tif");
displayLayers(imageFile);
}
	public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }
    private void displayLayers(File rasterFile) throws Exception {
        try{
    	AbstractGridFormat format = GridFormatFinder.findFormat( rasterFile ); 
        //this is a bit hacky but does make more geotiffs work
        Hints hints = new Hints();
        if (format instanceof GeoTiffFormat) {
            hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
        }
        reader = format.getReader(rasterFile, hints);
        

        // Initially display the raster in greyscale using the
        // data from the first image band
        Style rasterStyle = createRGBStyle();//createGreyscaleStyle(1);

        // Connect to the shapefile
        File shpFile = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/GREEN.shp");
        File shpFile1 = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/POLYGON.shp");
        File shpFile2 = new File("F:/workplace/Minor2/src/main/java/org/geotools/Minor2/ROADS.shp");
        
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(shpFile);
         shapefileSource = dataStore
                .getFeatureSource();
         FileDataStore dataStore1 = FileDataStoreFinder.getDataStore(shpFile1);
          shapefileSource1 = dataStore1
                 .getFeatureSource();
         FileDataStore dataStore2 = FileDataStoreFinder.getDataStore(shpFile2);
         shapefileSource2 = dataStore2
                 .getFeatureSource();
         setGeometry(); 
         final MapContent map = new MapContent();
         map.setTitle("ImageLab");
         
if(source.equalsIgnoreCase("Entry Gate") && destination.equalsIgnoreCase("Enrollment Office"))
{         filter=ECQL.toFilter("IN('ROADS.2')");
}
else if(source.equalsIgnoreCase("First Block") && destination.equalsIgnoreCase("Second Block")){
	filter=ECQL.toFilter("IN('ROADS.31','ROADS.32')");
}
       //create a SimpleFeatureCollection object for the filtered features
       SimpleFeatureCollection fc=shapefileSource2.getFeatures(filter);


       //create a feature iterator to traverse through the selected features
       SimpleFeatureIterator iter=fc.features();

       //create a Set object to store the featureIdentifiers.
       Set<FeatureId> IDs=new HashSet<FeatureId>();

       //add the selected features to IDs
       try{

           while(iter.hasNext()){

               SimpleFeature f=iter.next();

               IDs.add(f.getIdentifier());

               System.out.println(" "+f.getIdentifier());

           }
       }
       finally{

           iter.close();

       }


         /*
          * Create the JMapFrame and set it to display the shapefile's features
          * with a default line and colour style
          */
                 Style style = createSelectedStyle(IDs);

         // Create a basic style with yellow lines and no fill
        Style shpStyle = SLD.createPolygonStyle(Color.GREEN, null, 0.0f);
        Style shpStyle1 = SLD.createPolygonStyle(Color.RED, null, 0.0f);
        Style shpStyle2 = SLD.createPolygonStyle(Color.BLACK, null, 0.0f);
        
        // Set up a MapContent with the two layers
        
        Layer rasterLayer = new GridReaderLayer(reader, rasterStyle);
        map.addLayer(rasterLayer);
        
        Layer shpLayer = new FeatureLayer(shapefileSource, shpStyle);
        map.addLayer(shpLayer);
        Layer shpLayer1 = new FeatureLayer(shapefileSource1, shpStyle1);
        map.addLayer(shpLayer1);
        Layer shpLayer2 = new FeatureLayer(shapefileSource2, style);
        map.addLayer(shpLayer2);
     
     

        // Create a JMapFrame with a menu to choose the display style for the
        frame = new JMapFrame(map);
        frame.setSize(800, 600);
        frame.enableStatusBar(true);
        //frame.enableTool(JMapFrame.Tool.ZOOM, JMapFrame.Tool.PAN, JMapFrame.Tool.RESET);
        frame.enableToolBar(true);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Raster");
        menuBar.add(menu);

        menu.add( new SafeAction("Grayscale display") {
            public void action(ActionEvent e) throws Throwable {
                Style style = createRGBStyle();
                if (style != null) {
                    ((StyleLayer) map.layers().get(0)).setStyle(style);
                    frame.repaint();
                }
            }
        });

        menu.add( new SafeAction("RGB display") {
            public void action(ActionEvent e) throws Throwable {
                Style style = createRGBStyle();
                if (style != null) {
                    ((StyleLayer) map.layers().get(0)).setStyle(style);
                    frame.repaint();
                }
           }
        });
        // Finally display the map frame.
        // When it is closed the app will exit.
        frame.setVisible(true);
        
        }catch(Exception e2)
        {
        	System.out.println(e2);
        }
    }
    
    
    
    private void setGeometry() {
        GeometryDescriptor geomDesc = shapefileSource.getSchema().getGeometryDescriptor();
        geometryAttributeName = geomDesc.getLocalName();

        Class<?> clazz = geomDesc.getType().getBinding();

        if (Polygon.class.isAssignableFrom(clazz) ||
                MultiPolygon.class.isAssignableFrom(clazz)) {
            geometryType = GeomType.POLYGON;

        } else if (LineString.class.isAssignableFrom(clazz) ||
                MultiLineString.class.isAssignableFrom(clazz)) {

            geometryType = GeomType.LINE;

        } else {
            geometryType = GeomType.POINT;
        }

    }
        
    private Style createSelectedStyle(Set<FeatureId> IDs) {
        Rule selectedRule = createRule(SELECTED_COLOUR, SELECTED_COLOUR);
        selectedRule.setFilter(ff.id(IDs));

        Rule otherRule = createRule(LINE_COLOUR,FILL_COLOUR);
        otherRule.setElseFilter(true);

        FeatureTypeStyle fts = sf.createFeatureTypeStyle();
        fts.rules().add(selectedRule);
        fts.rules().add(otherRule);

        Style style2 = sf.createStyle();
        style2.featureTypeStyles().add(fts);

        return style2;
    }
    private Rule createRule(Color outlineColor, Color fillColor) {
  	  Symbolizer symbolizer = null;
  	  Fill fill = null;//not required if working with line
  	  Stroke stroke = sf.createStroke(ff.literal(outlineColor), ff.literal(LINE_WIDTH));

  	  symbolizer = sf.createLineSymbolizer(stroke, "the_geom");

  	  Rule rule = sf.createRule();
  	  rule.symbolizers().add(symbolizer);
  	  return rule;
  	}

    private Style createRGBStyle() {
        GridCoverage2D cov = null;
        try {
            cov = reader.read(null);
        } catch (IOException giveUp) {
            throw new RuntimeException(giveUp);
        }
        // We need at least three bands to create an RGB style
        int numBands = cov.getNumSampleDimensions();
        if (numBands < 3) {
            return null;
        }
        // Get the names of the bands
        String[] sampleDimensionNames = new String[numBands];
        for (int i = 0; i < numBands; i++) {
            GridSampleDimension dim = cov.getSampleDimension(i);
            sampleDimensionNames[i] = dim.getDescription().toString();
        }
        final int RED = 0, GREEN = 1, BLUE = 2;
        int[] channelNum = { -1, -1, -1 };
        // We examine the band names looking for "red...", "green...", "blue...".
        // Note that the channel numbers we record are indexed from 1, not 0.
        for (int i = 0; i < numBands; i++) {
            String name = sampleDimensionNames[i].toLowerCase();
            if (name != null) {
                if (name.matches("red.*")) {
                    channelNum[RED] = i + 1;
                } else if (name.matches("green.*")) {
                    channelNum[GREEN] = i + 1;
                } else if (name.matches("blue.*")) {
                    channelNum[BLUE] = i + 1;
                }
            }
        }
        // If we didn't find named bands "red...", "green...", "blue..."
        // we fall back to using the first three bands in order
        if (channelNum[RED] < 0 || channelNum[GREEN] < 0 || channelNum[BLUE] < 0) {
            channelNum[RED] = 1;
            channelNum[GREEN] = 2;
            channelNum[BLUE] = 3;
        }
        // Now we create a RasterSymbolizer using the selected channels
        SelectedChannelType[] sct = new SelectedChannelType[cov.getNumSampleDimensions()];
        ContrastEnhancement ce = sf.contrastEnhancement(ff.literal(1.0), ContrastMethod.NORMALIZE);
        for (int i = 0; i < 3; i++) {
            sct[i] = sf.createSelectedChannelType(String.valueOf(channelNum[i]), ce);
        }
        RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
        ChannelSelection sel = sf.channelSelection(sct[RED], sct[GREEN], sct[BLUE]);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);
    }


	public void actionPerformed(ActionEvent e)
	{
		String z=e.getActionCommand();
		if(z.equals("Calculate Path"))
		{
		 source=(String)sr1.getSelectedItem();
			 destination=(String)ds1.getSelectedItem();
			System.out.println("Source"+source+" Destination"+destination);
			try {
				getLayersAndDisplay();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			System.out.println("Wrong Values");
		}

		
	}
}