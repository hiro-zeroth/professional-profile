particlesJS("particles-js", {
  particles: {
    number: { value: 90, density: { enable: true, value_area: 800 } },
    color: { value: "#66fcf1" },
    shape: { type: "circle" },
    opacity: { value: 0.5 },
    size: { value: 3 },
    line_linked: {
      enable: true,
      distance: 150,
      color: "#66fcf1",
      opacity: 0.4,
      width: 1,
    },
    move: {
      enable: true,
      speed: 2,
      direction: "none",
      random: false,
      straight: false,
      out_mode: "out",
      attract: {
        enable: true,   // turn on attraction
        rotateX: 600,   // horizontal pull strength
        rotateY: 1200,  // vertical pull strength
      },
    },
  },
  interactivity: {
    detect_on: "canvas",
    events: {
      onhover: { enable: true, mode: "grab" },   // link lines to cursor
      onclick: { enable: true, mode: "push" },
      resize: true,
    },
    modes: {
      grab: { distance: 180, line_linked: { opacity: 0.8 } },
      push: { particles_nb: 4 },
    },
  },
  retina_detect: true,
});
