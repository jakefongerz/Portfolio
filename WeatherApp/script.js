
var lat;
var lon;

const app = Vue.createApp({
  data() {
    return {
      location: "loading...",
      coordinates: "Loading..",
      current_temp: "Loading...",
      current_max: "Fetchinginging",
      current_min: "getting her rn...",
      current_hum: "getting atm..",
      current_pres: "loading her up",
      current_description: "Loading",
      forecast: [],
      states: Array.from({ length: 40}, (_, i) => `${"gray"}`),
      unlikely: 0,
      neutral: 40,
      likely: 0,
      errorApi: false,

    };
  },
  mounted() {
    this.go_fetch(); // call the fetch function after the Vue app has been mounted
  },
  methods: {
    go_fetch() {
      console.log("fetching ipgeolocation");
      fetch("https://ipgeolocation.abstractapi.com/v1/?api_key=791b6c2490264b36ad7d2d57ec563b8e")
        .then(r => r.json())
        .then(json => {
          this.location = `${json.city}, ${json.region}, ${json.country}`;
          this.coordinates = `${json.latitude}, ${json.longitude}`
          lat = json.latitude
          lon = json.longitude
          console.log("resolved ipgeolocation \nLocation:" + this.location);
          console.log("fetching current weather");
          return fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${json.latitude}&lon=${json.longitude}&appid=71602a762974280a23f6c9567076bfd3`);

        })

        .then(r => r.json())
        .then(json => {
          console.log("we got the current weather!");
          this.current_temp = `${((json.main.temp - 273.15) * 9/5 + 32).toFixed(2)}°F`;
          this.current_max = `${((json.main.temp_max - 273.15) * 9/5 + 32).toFixed(2)}°F`;
          this.current_min = `${((json.main.temp_min - 273.15) * 9/5 + 32).toFixed(2)}°F`;
          this.current_hum = json.main.humidity;
          this.current_pres = json.main.pressure;
          this.current_description = json.weather[0].description;
          console.log(json)
          console.log("fetching post 3-5 forcast");
          return fetch(`https://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&appid=71602a762974280a23f6c9567076bfd3&units=imperial`)
        })
        .then(r => r.json())
        .then(json => this.forecast = json.list)
        .catch(error => {
        console.error("An error occurred in getting one of the apis", error);

        this.errorApi = true;
        });



    },
     toggle(ev) {
        let idx = ev.currentTarget.getAttribute('data-index');
        const currentState = this.states[idx];
        let newState;

        if (currentState === 'gray') {
            newState = 'green'
            this.neutral--
            this.likely++
            }
        else if (currentState === 'green') {
            newState = 'red'
            this.likely--;
            this.unlikely++
            }
        else {
            newState = 'gray'
            this.unlikely--;
            this.neutral++;
    }
        this.states[idx] = newState
                    },

  }
});

const vm = app.mount("#app");






