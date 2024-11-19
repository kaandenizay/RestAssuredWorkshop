package exercises.pojo;

import java.util.List;

public class CarManufacturers {
    public class Results{


        public class VehicleTypes{
            private boolean IsPrimary;
            private String Name;

            public boolean isPrimary() {
                return IsPrimary;
            }

            public void setPrimary(boolean primary) {
                IsPrimary = primary;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }
        }

        private List<VehicleTypes> VehicleTypes;
        private String Mfr_Name;

        public List<VehicleTypes> getVehicleTypes() {
            return VehicleTypes;
        }

        public void setVehicleTypes(List<VehicleTypes> vehicleTypes) {
            this.VehicleTypes = vehicleTypes;
        }

        public String getMfr_Name() {
            return Mfr_Name;
        }

        public void setMfr_Name(String mfr_Name) {
            Mfr_Name = mfr_Name;
        }
    }

    private int Count;

    private String Message;

    private  String SearchCriteria;
    private List<Results> Results;



    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        this.Count = count;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSearchCriteria() {
        return SearchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        SearchCriteria = searchCriteria;
    }

    public List<Results> getResults() {
        return Results;
    }

    public void setResults(List<Results> results) {
        this.Results = results;
    }


}
