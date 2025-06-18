import React, { useState } from "react";
import '../UploadImages/imageUpload.css';

export default function ImageUpload({ onSelectFiles }) {
  const [files, setFiles] = useState([]);
  const [previews, setPreviews] = useState([]);

  const handleFiles = (newFiles) => {
    const fileArray = Array.from(newFiles);
    const updatedFiles = [...files, ...fileArray];

    setFiles(updatedFiles);
    setPreviews(updatedFiles.map(file => URL.createObjectURL(file)));
    onSelectFiles(updatedFiles);
  };

  const handleFileChange = (e) => {
    handleFiles(e.target.files);
  };

  const handleDrop = (e) => {
    e.preventDefault();
    handleFiles(e.dataTransfer.files);
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleRemove = (index) => {
    const updatedFiles = files.filter((_, i) => i !== index);
    setFiles(updatedFiles);
    setPreviews(updatedFiles.map(file => URL.createObjectURL(file)));
    onSelectFiles(updatedFiles);
  };

  return (
    <div
      className="image-upload-box"
      onDrop={handleDrop}
      onDragOver={handleDragOver}
    >
      <p>Sube imágenes de tu alojamiento</p>
      <p>Arrastre y suelte imágenes aquí o haga clic para seleccionar archivos</p>

      <label htmlFor="image-upload" className="btn-upload">
        Seleccionar Imágenes
        <input
          id="image-upload"
          type="file"
          multiple
          accept="image/*"
          onChange={handleFileChange}
          style={{ display: "none" }}
        />
      </label>

      {previews.length > 0 && (
        <div className="image-preview-container">
          {previews.map((url, index) => (
            <div key={index} className="preview-wrapper">
              <img src={url} alt={`Preview ${index}`} className="image-preview" />
              <button
                type="button"
                className="remove-btn"
                onClick={() => handleRemove(index)}
              >
                ×
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
