package cctech_problems;

import java.util.ArrayList;

public class CalculateLengthFunction {

	static class CreatedPoint{
		double x;
		double y;
		public CreatedPoint(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	public int totalExposedToLight(ArrayList<CreatedPoint[]> buildings, CreatedPoint p) {
		CreatedPoint l[] = leftEdge(buildings);
		CreatedPoint r[] = rightEdge(buildings);
		int o = orientationOfPoint(l[0], p, l[1]);
		int o2 = orientationOfPoint(r[0], p, r[1]);
		double heigts[] = getHeights(buildings);
		double len=0;
		// check if source is on left side
		if (o == 2 || o == 0) {// counter clockwise
			// go in left to right direction
			double cur = heigts[0];
			len = heigts[0];// first side is always in light

			for (double h : heigts) {
				if (cur < h) {
					len = len + (h - cur);
				}
			}
		} else {
			// rigth of x but check if also right of right edge
			if (o2 == 1 || o2 == 0) {
				// go in right to left direction
				double cur = heigts[heigts.length - 1];
				len = heigts[heigts.length - 1];// first side is always in light

				for (int i = heigts.length - 2; i >= 0; i--) {
					if (cur < heigts[i]) {
						len = len + (heigts[i] - cur);// substracting shadow
					}
				}
			}
		}
		return (int) len;

	}

	private CreatedPoint[] rightEdge(ArrayList<CreatedPoint[]> buildings) {
		CreatedPoint right[] = new CreatedPoint[2];

		for (CreatedPoint[] b : buildings) {
			double maxx = b[0].x;
			for (int i = 1; i < b.length; i++) {
				if (maxx < b[i].x) {
					right[0] = b[i];
					maxx = b[i].x;
					right[1] = b[i - 1];
				}
			}
		}

		return right;
	}

	private CreatedPoint[] leftEdge(ArrayList<CreatedPoint[]> buildings) {
		CreatedPoint left[] = new CreatedPoint[2];
		for (CreatedPoint[] b : buildings) {
			double minx = b[0].x;
			for (int i = 1; i < b.length; i++) {
				if (minx > b[i].x) {
					left[0] = b[i];
					minx = b[i].x;
					left[1] = b[i - 1];
				}
			}
		}
		return left;
	}

	private int orientationOfPoint(CreatedPoint p, CreatedPoint q, CreatedPoint r) {
		int result = (int) ((q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y));

		if (result == 0) {// co-linear
			return 0;
		}
		return (result > 0) ? 1 : 2; // clock/counterclock wise
	}

	// get maximum height of buildings
	// we will traverse these in left to right or right to left of source
	private double[] getHeights(ArrayList<CreatedPoint[]> buildings) {
		double hights[] = new double[buildings.size()];
		int i = 0;
		for (CreatedPoint[] b : buildings) {
			double topy = b[0].y, bottomy = b[0].y;
			for (CreatedPoint c : b) {
				if (topy < c.y)
					topy = c.y;
				if (bottomy > c.y)
					bottomy = c.y;
			}
			double h = topy - bottomy;
			hights[i] = h;
			i++;
		}
		for (double h : hights) {
			System.out.println(h);
		}
		return hights;

	}

}