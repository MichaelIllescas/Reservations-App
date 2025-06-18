import React, { useState } from "react";
import "./FullGalleryViewer.css";

function FullGalleryViewer({ images, onClose }) {
  const [current, setCurrent] = useState(0);
  const [zoom, setZoom] = useState(false);

  const next = () => setCurrent((prev) => (prev + 1) % images.length);
  const prev = () => setCurrent((prev) => (prev - 1 + images.length) % images.length);

  return (
    <div className="fullscreen-overlay" onClick={onClose}>
      <div className="fullscreen-content" onClick={(e) => e.stopPropagation()}>
        <button className="close-fullscreen" onClick={onClose}>×</button>
        <button className="arrow left" onClick={prev}>←</button>
        <div className={`fullscreen-image-container ${zoom ? "zoomed" : ""}`} onClick={() => setZoom(!zoom)}>
          <img src={images[current]} alt={`img-${current}`} />
        </div>
        <button className="arrow right" onClick={next}>→</button>
      </div>
    </div>
  );
}

export default FullGalleryViewer;
