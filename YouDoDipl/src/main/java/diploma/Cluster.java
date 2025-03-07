package diploma;

import java.util.ArrayList;

import diploma.Coordinate;

public class Cluster {
	private int numOfPoints;
	private Coordinate center;
	
	public Cluster( ArrayList<Coordinate> cluster) {
		this.numOfPoints = cluster.size();
		this.center = getCenterOfCluster(cluster);
	}
	
	private Coordinate getCenterOfCluster(ArrayList<Coordinate> cluster) {
		Coordinate firstPoint = cluster.get(0);
	    double minLat = firstPoint.getLatitude(), maxLat = firstPoint.getLatitude(), minLong = firstPoint.getLongitude(), maxLong = firstPoint.getLongitude();
	    for(int i=1; i<cluster.size(); i++) {
	    		Coordinate c = cluster.get(i);
	    		
	    		if( c.getLatitude() < minLat ) {
	    			minLat = c.getLatitude();
	    		}
	    		if( c.getLatitude() > maxLat ) {
	    			maxLat = c.getLatitude();
	    		}
	    		if( c.getLongitude() < minLong ) {
	    			minLong = c.getLongitude();
	    		}
	    		if( c.getLongitude() > maxLong ) {
	    			maxLong = c.getLongitude();
	    		}
	    }
	    
	    double centreLat = (minLat + maxLat) / 2;
	    double centreLong = (minLong + maxLong) / 2;
	    
	    return new Coordinate(centreLat, centreLong);

	}
	
	private String getSVGIcon() {
		String icon = "data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2238%22%20height%3D%2238%22%20viewBox%3D%220%200%2038%2038%22%3E%3Cpath%20fill%3D%22%23a22%22%20stroke%3D%22%23ccc%22%20stroke-width%3D%22.5%22%20d%3D%22M34.305%2016.234c0%208.83-15.148%2019.158-15.148%2019.158S3.507%2025.065%203.507%2016.1c0-8.505%206.894-14.304%2015.4-14.304%208.504%200%2015.398%205.933%2015.398%2014.438z%22%2F%3E%3Ctext%20transform%3D%22translate%2819%2018.5%29%22%20fill%3D%22%23fff%22%20style%3D%22font-family%3A%20Arial%2C%20sans-serif%3Bfont-weight%3Abold%3Btext-align%3Acenter%3B%22%20font-size%3D%2212%22%20text-anchor%3D%22middle%22%3E" 
						+ this.numOfPoints
					+ "%3C%2Ftext%3E%3C%2Fsvg%3E";
		return icon;
	}
	
	public String getMarkerString() {
		String js_code = " myLatLng = new google.maps.LatLng( "+this.center.getLatitude() +","+ this.center.getLongitude() + ");\n"
				+" markers.push( new google.maps.Marker({ "
			      +"position: myLatLng,"
			      +" map: map,"
			      + "icon:  \" " + getSVGIcon() + " \" "
		    		+" }) ); ";
		
		return js_code;
	}
	
	public String getLocationAsArray() {
		return "[" + this.center.getLatitude() + "," + this.center.getLongitude() + ", '" + this.numOfPoints + "' ]";	
	}
	

}
