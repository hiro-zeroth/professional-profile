document.addEventListener("DOMContentLoaded", () => {
  console.log("Skills page loaded.");

  const bars = document.querySelectorAll(".progress-bar span");

  // Animate progress bars
  bars.forEach((bar, index) => {
    const skillLevel = bar.getAttribute("data-skill");
    setTimeout(() => {
      bar.style.width = skillLevel + "%";
    }, 200 * index);
  });
});
