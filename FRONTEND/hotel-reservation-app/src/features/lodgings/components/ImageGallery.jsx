import { useState } from "react";
import ImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "../styles/ProductImageGallery.css";

const BASE_URL = "http://localhost:8080"; // o usá process.env.REACT_APP_BACKEND_URL

function ProductImageGallery({ images = [] }) {
  const [showFullView, setShowFullView] = useState(false);
  const [current, setCurrent] = useState(0);
  const [zoomed, setZoomed] = useState(false);
  const [transformOrigin, setTransformOrigin] = useState("50% 50%");

  // Si no hay imágenes, mensaje de fallback
  if (!Array.isArray(images) || images.length === 0) {
    return <p className="text-muted">Sin imágenes disponibles.</p>;
  }

  // Armar los objetos con URL completa
  const formattedImages = images.map((img) => {
    const url = img.startsWith("http") ? img : `${BASE_URL}${img}`;
    return {
      original: url,
      thumbnail: url,
    };
  });

  const handleNext = () => {
    setZoomed(false);
    setCurrent((prev) => (prev + 1) % images.length);
  };

  const handlePrev = () => {
    setZoomed(false);
    setCurrent((prev) => (prev - 1 + images.length) % images.length);
  };

  const handleZoom = (e) => {
    const rect = e.currentTarget.getBoundingClientRect();
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
        {images.length > 1 && (
          <button className="view-more-overlay" onClick={() => setShowFullView(true)}>
            Ver más
          </button>
        )}
      </div>

      {showFullView && (
        <div className="fullscreen-overlay" onClick={() => {
          setShowFullView(false);
          setZoomed(false);
        }}>
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
              <img
                src={
                  images[current].startsWith("http")
                    ? images[current]
                    : `${BASE_URL}${images[current]}`
                }
                alt={`img-${current}`}
              />
            </div>
            <button className="arrow right" onClick={handleNext}>→</button>
          </div>
        </div>
      )}
    </>
  );
}

export default ProductImageGallery;
