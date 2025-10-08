// Interactive gradient effect following the cursor
const bg = document.getElementById("interactive-bg");

document.addEventListener("mousemove", (e) => {
  const x = e.clientX / window.innerWidth;
  const y = e.clientY / window.innerHeight;
  const color1 = `rgba(${20 + x * 80}, ${40 + y * 100}, 200, 0.3)`;
  const color2 = `rgba(${10 + y * 80}, ${200 - x * 100}, 150, 0.2)`;
  
  bg.style.background = `radial-gradient(circle at ${x * 100}% ${y * 100}%, ${color1}, ${color2}, #0b0c10)`;
});

console.log("Education page loaded with interactive gradient background.");
