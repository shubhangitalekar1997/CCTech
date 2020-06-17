package cctech_problems;

public class PointInPolygonCheckFunction {
	static int EXTREME_RIGHT = Integer.MAX_VALUE;
	static class CreatedPoint{
		double x;
		double y;

		public CreatedPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	boolean isInside(CreatedPoint polygon[], CreatedPoint pointToCheck) {
		int n= polygon.length;
		if (n < 3) {
			return false;
		}
		CreatedPoint extreme = new CreatedPoint(EXTREME_RIGHT, pointToCheck.y);
		// Count intersections of the above line with sides of polygon
		int count = 0, i = 0;

		do {
			int next = (i + 1) % n;

			// Check if the line segment from point to its extreme intersects with the line
			if (isIntersects(polygon[i], polygon[next], pointToCheck, extreme)) {

				// checking for point on any edge of polygon
				if (orientation(polygon[i], pointToCheck, polygon[next]) == 0) {
					return isOnSegment(polygon[i], pointToCheck, polygon[next]);
				}

				count++;
			}
			i = next;
		} while (i != 0);

		return (count % 2 == 1);
	}

	/* function to check if given point m lies between point l and point r */
	boolean isOnSegment(CreatedPoint l, CreatedPoint m, CreatedPoint r) {
		double max_x = (l.x > r.x) ? l.x : r.x;
		double min_x = (l.x < r.x) ? l.x : r.x;
		double max_y = (l.y > r.y) ? l.y : r.y;
		double min_y = (l.y < r.y) ? l.y : r.y;
		
		//can be done using Math.max and Math.min but instructions are of NO USE OF BUILT-IN FUNCTION

		if (m.x <= max_x && m.x >= min_x && m.y <= max_y && m.y >= min_y) {
			return true;
		}
		return false;
	}

	
	static int orientation(CreatedPoint p, CreatedPoint q, CreatedPoint r) {
		int result = (int) ((q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y));

		if (result == 0) {
			return 0;
		}
		return (result > 0) ? 1 : 2; 
	}

	// -------------------------------check for intersection
	boolean isIntersects(CreatedPoint p1, CreatedPoint p2, CreatedPoint p3, CreatedPoint p4) {

		int o1 = orientation(p1, p2, p3);
		int o2 = orientation(p1, p2, p4);
		int o3 = orientation(p3, p4, p1);
		int o4 = orientation(p3, p4, p2);

		
		if (o1 != o2 && o3 != o4) {
			return true;
		}

				if (o1 == 0 && isOnSegment(p1, p3, p2)) {
			return true;
		}
		

				if (o2 == 0 && isOnSegment(p1, p4, p2)) {
			return true;
		}

				if (o3 == 0 && isOnSegment(p3, p1, p4)) {
			return true;
		}
				if (o4 == 0 && isOnSegment(p3, p2, p4)) {
			return true;
		}

		return false;
	}

}
