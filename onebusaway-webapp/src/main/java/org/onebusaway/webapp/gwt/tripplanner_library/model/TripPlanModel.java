package org.onebusaway.webapp.gwt.tripplanner_library.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.onebusaway.transit_data.model.tripplanning.ItinerariesBean;
import org.onebusaway.transit_data.model.tripplanning.ItineraryBean;
import org.onebusaway.transit_data.model.tripplanning.LegBean;
import org.onebusaway.webapp.gwt.common.model.AbstractModel;

public class TripPlanModel extends AbstractModel<TripPlanModel> {

  private static ItineraryBeanComparator _comparator = new ItineraryBeanComparator();

  private List<ItineraryBean> _trips;

  private int _selectedIndex = 0;

  public void setTripPlans(ItinerariesBean trips) {
    _trips = trips.getItineraries();
    Collections.sort(_trips, _comparator);
    _selectedIndex = _trips.isEmpty() ? -1 : 0;
    fireModelChange(this);
  }

  public List<ItineraryBean> getTrips() {
    return _trips;
  }

  public int getSelectedIndex() {
    return _selectedIndex;
  }

  public void setSelectedIndex(int selectedIndex) {
    _selectedIndex = selectedIndex;
    fireModelChange(this);
  }

  private static class ItineraryBeanComparator implements Comparator<ItineraryBean> {

    public int compare(ItineraryBean o1, ItineraryBean o2) {

      List<LegBean> legs1 = o1.getLegs();
      List<LegBean> leg2 = o2.getLegs();

      if (legs1.isEmpty() && leg2.isEmpty())
        return 0;
      else if (legs1.isEmpty())
        return -1;
      else if (leg2.isEmpty())
        return 1;

      long d1 = o1.getEndTime();
      long d2 = o2.getEndTime();

      return d1 == d2 ? 0 : (d1 < d2 ? -1 : 1);
    }
  }
}
