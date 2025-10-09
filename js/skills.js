document.addEventListener("DOMContentLoaded", () => {
  console.log("Skills page loaded.");

  const bars = document.querySelectorAll(".progress-bar span");
  const bg = document.getElementById("interactive-bg");

  // Animate progress bars
  bars.forEach((bar, index) => {
    const skillLevel = bar.getAttribute("data-skill");
    setTimeout(() => {
      bar.style.width = skillLevel + "%";
    }, 200 * index);
  });

  // Colorful gradient background (same feel as education)
  document.addEventListener("mousemove", (e) => {
    const x = e.clientX / window.innerWidth;
    const y = e.clientY / window.innerHeight;
    const color1 = `rgba(${100 + x * 155}, ${50 + y * 205}, ${200 - y * 80}, 0.6)`;
    const color2 = `rgba(${255 - x * 155}, ${100 + y * 105}, ${150 + x * 80}, 0.6)`;
    
    bg.style.background = `radial-gradient(circle at ${x * 100}% ${y * 100}%, ${color1}, ${color2}, #0b0c10)`;
  });
});
