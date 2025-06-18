import { useState } from "react";
import ImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "../styles/ProductImageGallery.css";

function ProductImageGallery({ images }) {
  const [showFullView, setShowFullView] = useState(false);
  const [current, setCurrent] = useState(0);
  const [zoomed, setZoomed] = useState(false);
  const [transformOrigin, setTransformOrigin] = useState("50% 50%");

  const formattedImages = images.map((img) => ({
    original: img,
    thumbnail: img,
  }));

  const handleNext = () => {
    setZoomed(false);
    setCurrent((prev) => (prev + 1) % images.length);
  };

  const handlePrev = () => {
    setZoomed(false);
    setCurrent((prev) => (prev - 1 + images.length) % images.length);
  };

  const handleZoom = (e) => {
    const container = e.currentTarget;
    const rect = container.getBoundingClientRect();
    const x = ((e.clientX - rect.left) / rect.width) * 100;
    const y = ((e.clientY - rect.top) / rect.height) * 100;
    setTransformOrigin(`${x}% ${y}%`);
    setZoomed(!zoomed);
  };

  return (
    <>
      <div className="image-gallery-wrapper container-galery">
        <ImageGallery
          items={formattedImages.slice(0, 5)}
          showPlayButton={false}
          showFullscreenButton={false}
          showNav={true}
          thumbnailPosition="right"
         
          onClick={() => setZoomed(false)}
        />
        <button className="view-more-overlay" onClick={() => setShowFullView(true)}>
          Ver más
        </button>
      </div>

      {showFullView && (
        <div className="fullscreen-overlay" onClick={() => setShowFullView(false)}>
          <div className="fullscreen-content" onClick={(e) => e.stopPropagation()}>
            <button className="close-fullscreen" onClick={() => setShowFullView(false)}>×</button>
            <button className="arrow left" onClick={handlePrev}>←</button>
            <div
              className="fullscreen-image-container"
              onClick={handleZoom}
              style={{
                transform: zoomed ? "scale(1.8)" : "scale(1)",
                transformOrigin: transformOrigin,
                cursor: zoomed ? "zoom-out" : "zoom-in",
                transition: "transform 0.3s ease, transform-origin 0.3s ease",
              }}
            >
              <img src={images[current]} alt={`img-${current}`} />
            </div>
            <button className="arrow right" onClick={handleNext}>→</button>
          </div>
        </div>
      )}
    </>
  );
}

export default ProductImageGallery;
