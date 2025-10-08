/* ---- particles.js config ---- */
particlesJS("particles-js", {
  particles: {
    number: {
      value: 380,
      density: { enable: true, value_area: 800 },
    },
    color: { value: "#ffffff" },
    shape: {
      type: "circle",
      stroke: { width: 0, color: "#000000" },
      polygon: { nb_sides: 5 },
    },
    opacity: {
      value: 0.5,
      random: false,
    },
    size: {
      value: 3,
      random: true,
    },
    line_linked: {
      enable: true,
      distance: 150,
      color: "#ffffff",
      opacity: 0.4,
      width: 1,
    },
    move: {
      enable: true,
      speed: 6,
      direction: "none",
      random: false,
      straight: false,
      out_mode: "out",
      bounce: false,
    },
  },
  interactivity: {
    detect_on: "canvas",
    events: {
      onhover: { enable: true, mode: "grab" },
      onclick: { enable: true, mode: "push" },
      resize: true,
    },
    modes: {
      grab: {
        distance: 140,
        line_linked: { opacity: 1 },
      },
      push: { particles_nb: 4 },
    },
  },
  retina_detect: true,
});

/* ---- stats.js config ---- */
var count_particles, stats, update;
stats = new Stats();
stats.setMode(0);
stats.domElement.style.position = "absolute";
stats.domElement.style.left = "0px";
stats.domElement.style.top = "0px";
document.body.appendChild(stats.domElement);
count_particles = document.querySelector(".js-count-particles");

update = function () {
  stats.begin();
  stats.end();
  if (
    window.pJSDom[0].pJS.particles &&
    window.pJSDom[0].pJS.particles.array
  ) {
    count_particles.innerText =
      window.pJSDom[0].pJS.particles.array.length;
  }
  requestAnimationFrame(update);
};
requestAnimationFrame(update);
